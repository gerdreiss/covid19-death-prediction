package covid19

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession

object Analysis extends App {
  // Set the log level to only print errors
  Logger.getLogger("org").setLevel(Level.ERROR)

  val spark = SparkSession
    .builder
    .appName("Covid19DeathPrediction")
    .master("local[*]")
    .getOrCreate()

  import spark.implicits._
  val people = spark
    .read
    .option("header", "true")
    .option("inferSchema", "true")
    .csv("data/CovidData.csv")
  // .as[Data]

  people.show()

}
