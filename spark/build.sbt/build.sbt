name := "MyFirstSparkProgram"

version := "1.0"

scalaVersion = "2.12.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.4.8" % "provided",
  "org.apache.spark" %% "spark-core" % "2.4.8" % "provided"
)

