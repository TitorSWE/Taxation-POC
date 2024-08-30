package ch.imposition.perception

import akka.actor.typed.{ActorSystem, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import ch.imposition.perception.infrastructure.exchange.ExchangeService

object RootActor {
  sealed trait Command
  case class Boot(actorSystem: ActorSystem[_]) extends Command

  def apply(): Behavior[Command] = Behaviors.receive { (context, message) =>
    message match {
      case Boot(actorSystem) =>
        context.log.info("Booting the system ...")
        // Model init

        // Infrastructure init
        ExchangeService.boot(context)


        Behaviors.same
    }
  }
}
