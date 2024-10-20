package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.model.ResponseCity
import com.example.testdoubletemplate.model.toPrefecture
import com.example.testdoubletemplate.model.toResponseCity
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
        return cityRepository.findAllCities()
            .map { it.toResponseCity() }
    }

    override fun findCitiesByPrefecture(prefecture: String): List<ResponseCity> {
        return cityRepository.findCitiesByPrefecture(prefecture)
            .map { it.toResponseCity() }
    }

    override fun findCityByCityName(cityName: String): ResponseCity? {
        return cityRepository.findCityByCityName(cityName)?.toResponseCity()
    }

    override fun findBelongPrefectureByCityName(cityName: String): Prefecture? {
        return cityRepository.findCityByCityName(cityName)?.toPrefecture()
    }
}