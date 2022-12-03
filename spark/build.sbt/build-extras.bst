val scala3Version = "3.2.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "project1",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

libraryDependencies ++= Seq(
  ("org.apache.spark" %% "spark-sql" % "3.2.0" % "provided").cross(CrossVersion.for3Use2_13),
  "org.scalameta" %% "munit" % "0.7.29" % Test,
  "junit" % "junit" % "4.10" % Test,
    "org.scalactic" %% "scalactic" % "3.2.14",
      "org.apache.commons" % "commons-lang3" % "3.12.0",
  //"org.scalatest" %% "scalatest" % "3.2.14" % "test",
))
/*   
 */

