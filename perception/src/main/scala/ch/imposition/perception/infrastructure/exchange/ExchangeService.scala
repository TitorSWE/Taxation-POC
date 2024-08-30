package ch.imposition.perception.infrastructure.exchange

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.ActorContext
import akka.stream.alpakka.amqp.*
import akka.stream.alpakka.amqp.scaladsl.{AmqpSink, AmqpSource, CommittableReadResult}
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString
import ch.imposition.perception.infrastructure.exchange.ExchangeReceiver.ProcessMessage
import com.rabbitmq.client.ConnectionFactory
import com.typesafe.config.ConfigFactory

import scala.util.{Failure, Success}

class ExchangeService(actorContext: ActorContext[_]) {

  implicit val actorSystem: ActorSystem[_] = actorContext.system
  // Load the configuration
  private val config = ConfigFactory.load()

  // Retrieve the AMQP configuration
  private val amqpConfig = config.getConfig("amqp")
  private val host = amqpConfig.getString("host")
  private val port = amqpConfig.getInt("port")
  private val username = amqpConfig.getString("username")
  private val password = amqpConfig.getString("password")

  // Set up the RabbitMQ ConnectionFactory
  private val connectionFactory: ConnectionFactory = new ConnectionFactory()
  connectionFactory.setHost(host)
  connectionFactory.setPort(port)
  connectionFactory.setUsername(username)
  connectionFactory.setPassword(password)

  // Define the queue name and exchange name
  private val queueName = "org.taxation.perception-queue"
  private val exchangeName = "org.taxation.perception-exchange"

  // Create the connection provider
  private val connectionProvider = AmqpConnectionFactoryConnectionProvider.create(connectionFactory)

  // Declare the queue and exchange
  private val queueDeclaration = QueueDeclaration(queueName).withDurable(true)
  private val exchangeDeclaration = ExchangeDeclaration(exchangeName, "fanout").withDurable(true)

  // Configure the source settings
  private val sourceSettings: NamedQueueSourceSettings = NamedQueueSourceSettings(
    connectionProvider = connectionProvider,
    queue = queueName
  ).withDeclaration(queueDeclaration)
    .withAckRequired(true) // Ensure manual acknowledgment

  // Create the AmqpSource
  private val amqpSource: Source[CommittableReadResult, _] = AmqpSource.committableSource(
    sourceSettings,
    bufferSize = 10 // Adjust the buffer size as needed
  )

  // Define the binding declaration
  private val bindingDeclaration = BindingDeclaration(queueName, exchangeName)

  // Create the AmqpSink
  private val amqpSink = AmqpSink.simple(
    AmqpWriteSettings(connectionProvider)
      .withExchange(exchangeName)
      .withDeclaration(exchangeDeclaration)
  )

  // External Event Handler
  private val exchangeReceiver = actorContext.spawn(ExchangeReceiver(), "exchange-receiver")

  println(exchangeReceiver.path.address)

  def run(): Unit = {
    // Create a Source from a list of messages
    val messages = List("Message 1", "Message 2", "Message 3")
    val messageSource = Source.fromIterator(() => messages.iterator.map(str => ByteString(str)))

    // Connect the Source to the Sink and run the stream
    messageSource.runWith(amqpSink)

    // Run the stream and process messages
    amqpSource.runWith(Sink.foreach { message =>
      // Process the message
      exchangeReceiver ! ProcessMessage(message)
    })
  }
}

object ExchangeService {
  def boot(actorContext: ActorContext[_]): Unit =
    val exchangeService: ExchangeService = ExchangeService(actorContext)
    exchangeService.run()
}
