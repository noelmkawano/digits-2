name := """digits"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.3-1102-jdbc4",
  "be.objectify"  %% "deadbolt-java" % "2.3.2",
  "com.feth" %% "play-authenticate" % "0.6.8"
)
