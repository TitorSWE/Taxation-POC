ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "perception"
  )

mainClass in Compile := Some("ch.imposition.perception.Main")

resolvers += "Akka library repository".at("https://repo.akka.io/maven")


val AkkaVersion = "2.9.5"
val AlpakkaVersion = "6.0.2" // Use the latest stable version


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream-typed" % AkkaVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % AlpakkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.6", // Logback implementation
  "org.slf4j" % "slf4j-api" % "2.0.12", // SLF4J API
  "com.typesafe" % "config" % "1.4.3", // Typesafe Config
  "com.typesafe.akka" %% "akka-persistence-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-persistence-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-serialization-jackson" % AkkaVersion
)
