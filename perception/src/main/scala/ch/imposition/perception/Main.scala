package ch.imposition.perception

import akka.actor.typed.ActorSystem
import akka.stream.alpakka.amqp.scaladsl.{AmqpSink, AmqpSource, CommittableReadResult}
import akka.stream.alpakka.amqp.{AmqpConnectionFactoryConnectionProvider, AmqpUriConnectionProvider, AmqpWriteSettings, BindingDeclaration, ExchangeDeclaration, NamedQueueSourceSettings, QueueDeclaration}
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString
import ch.imposition.perception.RootActor.Boot
import com.rabbitmq.client.ConnectionFactory
import com.typesafe.config.ConfigFactory

object Main {
  def main(args: Array[String]): Unit = {
    // Create the actor system
    implicit val system: ActorSystem[RootActor.Command] = ActorSystem(RootActor(), "actor-system")
    // Send a message to the actor
    system ! Boot(system)

    /*val config = ConfigFactory.load()

    // Retrieve the AMQP configuration
    val amqpConfig = config.getConfig("amqp")
    val host = amqpConfig.getString("host")
    val port = amqpConfig.getInt("port")
    val username = amqpConfig.getString("username")
    val password = amqpConfig.getString("password")

    // Set up the RabbitMQ ConnectionFactory
    val connectionFactory: ConnectionFactory = new ConnectionFactory()
    connectionFactory.setHost(host)
    connectionFactory.setPort(port)
    connectionFactory.setUsername(username)
    connectionFactory.setPassword(password)

    // Define the queue name
    val queueName = "org.taxation.perception-queue"
    val exchangeName = "org.taxation.perception-exchange"

    val connectionProvider = AmqpConnectionFactoryConnectionProvider.create(connectionFactory)

    val queueDeclaration = QueueDeclaration(queueName).withDurable(true)
    val exchangeDeclaration = ExchangeDeclaration(exchangeName, "fanout").withDurable(true)


    // Configure the source settings with queue declaration
    val sourceSettings: NamedQueueSourceSettings = NamedQueueSourceSettings(
      connectionProvider = connectionProvider,
      queue = queueName
    ).withDeclaration(queueDeclaration)
      .withAckRequired(true) // Ensure manual acknowledgment


    val amqpSource: Source[CommittableReadResult, _] = AmqpSource.committableSource(
      sourceSettings,
      bufferSize = 10 // Adjust the buffer size as needed
    )

    // Define the binding declaration
    val bindingDeclaration = BindingDeclaration(queueName, exchangeName)

    // Create the AmqpSink
    val amqpSink = AmqpSink.simple(
      AmqpWriteSettings(connectionProvider)
        .withExchange(exchangeName)
        .withDeclaration(exchangeDeclaration)
    )

    // Create a Source from a list of messages
    val messages = List("Message 1", "Message 2", "Message 3")
    val messageSource = Source.fromIterator(() => messages.iterator.map(str => ByteString(str)))

    // Connect the Source to the Sink and run the stream
    messageSource.runWith(amqpSink)

    // Run the stream and process messages
    amqpSource.runWith(Sink.foreach { message =>
      // Process the message
      val payload = message.message.bytes.utf8String
      println(s"Received message: $payload")
      // Acknowledge the message
      message.ack()
    })

*/

  }
}
