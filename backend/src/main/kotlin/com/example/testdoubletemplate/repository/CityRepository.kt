package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.DynamoDBGenerator
import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.entity.toCity
import com.example.testdoubletemplate.model.City
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

interface CityRepository {
    fun findAllCities(): List<City>
    fun findCitiesByPrefecture(prefecture: String): List<City>
    fun findCityByCityName(cityName: String): City?
}

@Repository
class DefaultCityRepository(
    dynamoDBGenerator: DynamoDBGenerator,
    @Value("\${spring.profiles.active}")
    private val environment: String
): CityRepository{
    private val dynamoDBRepository: NoSQLRepository<JapanTableEntity> =
        dynamoDBGenerator.build("japan_table_${environment}")
    override fun findAllCities(): List<City> {
        return dynamoDBRepository.findAllByPK("City").map { it.toCity() }
    }

    override fun findCitiesByPrefecture(prefecture: String): List<City> {
        return dynamoDBRepository.findAllByGSI(
            indexName = "BelongPrefectureGSI",
            pk = prefecture
        ).map { it.toCity() }
    }

    override fun findCityByCityName(cityName: String): City? {
        val item = dynamoDBRepository.findItemByPKandSK(
            pk = "City",
            sk = cityName
        )
        return item?.toCity()
    }

}