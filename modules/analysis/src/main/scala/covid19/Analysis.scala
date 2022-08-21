package covid19

import org.apache.log4j.{ Level, Logger }
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
    .schema(Encoders.product[RawData].schema)
    .option("header", "true")
    .csv("data/CovidData.csv")
    .as[RawData]
    .cache()

  // show data for all countries
  people
    .select("location", "year", "weeklyCases", "nextWeeksDeaths")
    .where(
      !$"location".isin("World", "Asia", "Africa", "Europe", "North America", "South America", "Upper middle income")
    )
    .groupBy("location", "year")
    .sum("weeklyCases", "nextWeeksDeaths")
    .sort($"location", $"year")
    .show(666, truncate = false)

  // show data for continents only
  people
    .select("location", "weeklyCases", "nextWeeksDeaths")
    .where($"location".isin("Africa", "Asia", "Europe", "North America", "South America"))
    .groupBy("location")
    .sum("weeklyCases", "nextWeeksDeaths")
    .sort($"sum(nextWeeksDeaths)".desc)
    .show(666, truncate = false)

  // show vaccinations to death ratio
  people
    .select("location", "totalVaccinations", "nextWeeksDeaths")
    .where(
      !$"location".isin(
        "World",
        "Asia",
        "Africa",
        "Europe",
        "European Union",
        "North America",
        "South America",
        "High income",
        "Lower middle income",
        "Upper middle income",
      )
    )
    .filter(!_.anyNull)
    .groupBy("location")
    .sum("totalVaccinations", "nextWeeksDeaths")
    .sort($"sum(nextWeeksDeaths)".desc)
    .as[(String, Double, Double)]
    .map(row => TotalVacToDeaths(row._1, f"${row._2}%.0f", f"${row._3}%.0f"))
    .show(666, truncate = false)

}
