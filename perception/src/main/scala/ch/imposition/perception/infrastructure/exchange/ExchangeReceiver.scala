package ch.imposition.perception.infrastructure.exchange

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import akka.persistence.typed.PersistenceId
import akka.stream.alpakka.amqp.scaladsl.CommittableReadResult
import akka.stream.alpakka.amqp.ReadResult
import akka.util.ByteString
import ch.imposition.perception.infrastructure.exchange.externalEvents.LiabilityTypeDefined
import ch.imposition.perception.model.debt.DebtEntity
import ch.imposition.perception.model.debt.DebtEntity.CreateDebt
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import scala.util.{Failure, Success, Try}
import java.util.UUID

object ExchangeReceiver {

  sealed trait Command
  final case class ProcessMessage(committableReadResult: CommittableReadResult) extends Command

  def apply(): Behavior[Command] = Behaviors.setup { context =>
    // Initialize ObjectMapper
    val mapper = new ObjectMapper().registerModule(DefaultScalaModule)

    Behaviors.receiveMessage {
      case ProcessMessage(committableReadResult) =>
        val readResult: ReadResult = committableReadResult.message
        val externalEventType = readResult.properties.getType

        externalEventType match {
          case "org.taxation.liability.model.events.LiabilityTypeDefined" =>
            Try {
              // Convert ByteString to a UTF-8 string
              val jsonString = readResult.bytes.utf8String
              // Deserialize the JSON string into a LiabilityTypeDefined object
              val event = mapper.readValue(jsonString, classOf[LiabilityTypeDefined])
              // Generate a unique ID for the actor
              val id = UUID.randomUUID().toString
              // Spawn a new DebtEntity actor
              val actorRef = context.spawn(DebtEntity(PersistenceId.ofUniqueId(id)), id)
              println(actorRef.path.address)
              // Send the CreateDebt command to the actor
              actorRef ! CreateDebt(liabilityId = event.liabilityId, personId = event.personId, year = event.year)
            } match {
              case Success(_) =>
                // Acknowledge the message
                committableReadResult.ack()
              case Failure(exception) =>
                // Handle the failure case
                context.log.error("Failed to process message", exception)
                // Optionally, you might want to nack the message or take other actions
                committableReadResult.nack()
            }

          case _ =>
            // Handle unexpected event types
            context.log.info(s"Unknown event type: $externalEventType")
            // Optionally, acknowledge the message even if it's not processed
            committableReadResult.ack()
        }
        Behaviors.same
    }
  }
}
