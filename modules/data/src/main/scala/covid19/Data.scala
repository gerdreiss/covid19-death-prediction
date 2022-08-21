package covid19

final case class RawData(
    id: Long,
    location: String,
    weeklyCases: Double,
    year: Int,
    weeklyCasesPerMillion: Double,
    weeklyDeaths: Double,
    weeklyDeathsPerMillion: Double,
    totalVaccinations: Double,
    peopleVaccinated: Double,
    peopleFullyVaccinated: Double,
    totalBoosters: Double,
    dailyVaccinations: Double,
    totalVaccinationsPerHundred: Double,
    peopleVaccinatedPerHundred: Double,
    peopleFullyVaccinatedPerHundred: Double,
    totalBoostersPerHundred: Double,
    dailyVaccinationsPerHundred: Double,
    dailyPeopleVaccinated: Double,
    dailyPeopleVaccinatedPerHundred: Double,
    nextWeeksDeaths: Double,
)

case class TotalVacToDeaths(
    location: String,
    totalVaccinations: String,
    nextWeeksDeaths: String,
)
