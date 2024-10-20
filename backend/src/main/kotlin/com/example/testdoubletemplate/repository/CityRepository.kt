package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.model.City
import org.springframework.stereotype.Repository

interface CityRepository {
    fun findAllCities(): List<City>
    fun findCitiesByPrefecture(prefecture: String): List<City>
    fun findCityByCityName(cityName: String): City?
}

@Repository
class DefaultCityRepository(
    val dynamoDBRepository: NoSQLRepository<JapanTableEntity>
): CityRepository{
    override fun findAllCities(): List<City> {
        val items = dynamoDBRepository.findAllByPK("City")

        return items.map { City(
            cityName = it.mainSk,
            belongPrefecture = it.belongPrefecture,
            areaOfPrefecture = it.areaOfPrefecture,
            populationOfPrefecture = it.populationOfPrefecture,
            metropolitan = it.metropolitan,
            areaOfCity = it.areaOfCity,
            populationOfCity = it.populationOfCity,
        ) }
    }

    override fun findCitiesByPrefecture(prefecture: String): List<City> {
        val items = dynamoDBRepository.findAllByGSI(
            indexName = "BelongPrefectureGSI",
            pk = prefecture
        )

        return items.map { City(
            cityName = it.mainSk,
            belongPrefecture = it.belongPrefecture,
            areaOfPrefecture = it.areaOfPrefecture,
            populationOfPrefecture = it.populationOfPrefecture,
            metropolitan = it.metropolitan,
            areaOfCity = it.areaOfCity,
            populationOfCity = it.populationOfCity,
        ) }
    }

    override fun findCityByCityName(cityName: String): City? {
        val item = dynamoDBRepository.findItemByPKandSK(
            pk = "City",
            sk = cityName
        )

        return item?.let { City(
            cityName = it.mainSk,
            belongPrefecture = it.belongPrefecture,
            areaOfPrefecture = it.areaOfPrefecture,
            populationOfPrefecture = it.populationOfPrefecture,
            metropolitan = it.metropolitan,
            areaOfCity = it.areaOfCity,
            populationOfCity = it.populationOfCity,
        ) }
    }

}