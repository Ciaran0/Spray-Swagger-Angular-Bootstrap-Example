name := "SprayPlayground"

version := "0.1"

scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")


libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"	  % "1.3.2",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test",
    "com.typesafe.akka" %% "akka-slf4j"      % akkaV,
    "com.typesafe.akka" %% "akka-actor"      % akkaV,
    "com.gettyimages" %% "spray-swagger" % "0.5.1",
    "org.slf4j" % "slf4j-api" % "1.7.7",
    "ch.qos.logback"  %  "logback-classic"   % "1.1.3",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "org.scalactic" %% "scalactic" % "2.2.6"
  )
}

Revolver.settings

