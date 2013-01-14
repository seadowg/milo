name := "milo"

version := "0.0.2"

scalaVersion := "2.9.2"


libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "1.12.1" % "test",
  "org.mockito" % "mockito-all" % "1.9.0" 
)

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                  "releases"  at "http://oss.sonatype.org/content/repositories/releases")
