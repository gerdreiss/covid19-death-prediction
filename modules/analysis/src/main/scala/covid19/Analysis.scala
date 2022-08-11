package covid19

import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.Encoder

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
    .schema(Encoders.product[Data].schema)
    .option("header", "true")
    .csv("data/CovidData.csv")
    .as[Data]

  people
    .select("location", "year", "weeklyCases", "nextWeeksDeaths")
    .where(
      ($"location" !== "World") &&
        ($"location" !== "Europe") &&
        ($"location" !== "Africa")
    )
    .groupBy("location", "year")
    .sum("weeklyCases", "nextWeeksDeaths")
    .sort($"location", $"year")
    .show()

}
