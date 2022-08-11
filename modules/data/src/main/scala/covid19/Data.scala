package covid19

final case class Data(
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
