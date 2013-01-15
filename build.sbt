name := "milo"

version := "0.1.0.rc1"

scalaVersion := "2.10.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "1.13" % "test",
  "org.mockito" % "mockito-all" % "1.9.0"
)

parallelExecution in Test := false
