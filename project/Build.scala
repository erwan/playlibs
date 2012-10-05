
import sbt._
import sbt.Default._
import sbt.Keys._

object ApplicationBuild extends Build {

    lazy val commonSettings: Seq[Setting[_]] = Project.defaultSettings ++ Seq(
        organization := "playlibs",
        scalaVersion := "2.9.2",
        version := "2.1-20121005",
        resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/releases",
        libraryDependencies += "org.specs2" %% "specs2" % "1.11" % "test"
    )

    lazy val sip14 = Project("sip14", file("sip14"), settings = commonSettings)

    lazy val json = Project("json", file("json"), settings = commonSettings ++ Seq(
      libraryDependencies += "org.codehaus.jackson" % "jackson-core-asl" % "1.9.9",
      libraryDependencies += "org.codehaus.jackson" % "jackson-mapper-asl" % "1.9.9",
      libraryDependencies += "joda-time" % "joda-time" % "2.1",
      libraryDependencies += "org.joda" % "joda-convert" % "1.2"
    )).dependsOn(sip14)

    lazy val ws = Project("ws", file("ws"), settings = commonSettings ++ Seq(
      libraryDependencies += "com.ning" % "async-http-client" % "1.7.6"
    )).dependsOn(json)

    lazy val root = Project("root", file(".")).aggregate(
        sip14, ws, json
    )

}

