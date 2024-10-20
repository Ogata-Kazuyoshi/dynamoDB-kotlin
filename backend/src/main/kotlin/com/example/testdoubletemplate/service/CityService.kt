package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.model.ResponseCity
import com.example.testdoubletemplate.repository.CityRepository
import org.springframework.stereotype.Service

interface CityService {
    fun findAllCities(): List<ResponseCity>
    fun findCitiesByPrefecture(prefecture: String): List<ResponseCity>
    fun findCityByCityName(cityName: String): ResponseCity?
    fun findBelongPrefectureByCityName(cityName: String): Prefecture?
}

@Service
class DefaultCityService(
    val cityRepository: CityRepository
): CityService {
    override fun findAllCities(): List<ResponseCity> {
        val citiesAllAttributes = cityRepository.findAllCities()

        return citiesAllAttributes.map { ResponseCity(
            cityName = it.cityName,
            areaOfCity = it.areaOfCity,
            populationOfCity = it.populationOfCity,
        )}
    }

    override fun findCitiesByPrefecture(prefecture: String): List<ResponseCity> {
        val citiesAllAttributes = cityRepository.findCitiesByPrefecture(prefecture)

        return citiesAllAttributes.map { ResponseCity(
            cityName = it.cityName,
            areaOfCity = it.areaOfCity,
            populationOfCity = it.populationOfCity,
        )}
    }

    override fun findCityByCityName(cityName: String): ResponseCity? {
        val cityAttribute = cityRepository.findCityByCityName(cityName)

        return cityAttribute?.let{ResponseCity(
            cityName = it.cityName,
            areaOfCity = it.areaOfCity,
            populationOfCity = it.populationOfCity,
        )}
    }

    override fun findBelongPrefectureByCityName(cityName: String): Prefecture? {
        val cityAttribute = cityRepository.findCityByCityName(cityName)

        return cityAttribute?.let { Prefecture(
            prefectureName = it.belongPrefecture,
            areaOfPrefecture = it.areaOfPrefecture,
            populationOfPrefecture = it.populationOfPrefecture,
            metropolitan = it.metropolitan,
        ) }
    }
}