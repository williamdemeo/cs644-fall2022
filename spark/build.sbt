val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "sparkproject",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
  )
libraryDependencies ++= Seq(
  ("org.apache.spark" %% "spark-sql" % "3.2.0" % "provided").cross(CrossVersion.for3Use2_13),
  "org.scalameta" %% "munit" % "0.7.29" % Test
)

// include the 'provided' Spark dependency on the classpath for `sbt run`
// Compile / run := Defaults.runTask(Compile / fullClasspath, Compile / run / mainClass, Compile / run / runner).evaluated
