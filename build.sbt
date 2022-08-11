Global / semanticdbEnabled := true // for metals

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "pro.reiss"
ThisBuild / organizationName := "reiss.pro"

lazy val `covid19-death-prediction` = project
  .in(file("."))
  .settings(name := "covid19-death-prediction")
  .aggregate(analysis, data)

lazy val analysis = project
  .in(file("modules/analysis"))
  .settings(
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.3.0",
      "org.apache.spark" %% "spark-sql"  % "3.3.0",
    )
  )
  .dependsOn(data)

lazy val data = project
  .in(file("modules/data"))
