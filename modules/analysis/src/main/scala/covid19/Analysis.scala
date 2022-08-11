package covid19

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.sql._

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
    .where(!$"location".isin("World", "Europe", "Africa", "North America", "South America", "Upper middle income"))
    .groupBy("location", "year")
    .sum("weeklyCases", "nextWeeksDeaths")
    .sort($"location", $"year")
    .show(660, false)

}
