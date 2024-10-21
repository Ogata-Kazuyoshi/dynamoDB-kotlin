package com.example.testdoubletemplate.controller

import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.model.ResponseCity
import com.example.testdoubletemplate.model.request.RequestPrefecture
import com.example.testdoubletemplate.service.CityService
import com.example.testdoubletemplate.service.PrefectureService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/japanInformation")
class JapanController(
    val prefectureService: PrefectureService,
    val cityService: CityService
) {
    @GetMapping("/prefectures")
    fun prefecturesController(): List<Prefecture> {
        return prefectureService.findAllPrefectures()
    }
    @PostMapping("/prefectures")
    fun putPrefecture(
        @RequestBody reqBody: RequestPrefecture
    ) {
        return prefectureService.putPrefecture(reqBody)
    }


    @GetMapping("/prefectures/areaSize")
    fun findPrefecturesByAreaSize(): List<Prefecture> {
        return prefectureService.findPrefecturesByAreaSize()
    }

    @GetMapping("/prefectures/population")
    fun findPrefecturesByPopulation(): List<Prefecture> {
        return prefectureService.findPrefecturesByPopulation()
    }

    @GetMapping("/prefectures/{prefectureName}")
    fun prefectureController(
        @PathVariable prefectureName: String
    ): Prefecture {
        return prefectureService.findPrefecture(prefectureName)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "not found prefecture")
    }

    @GetMapping("prefectures/by-city/{cityName}")
    fun prefectureByCityController(
        @PathVariable cityName: String
    ): Prefecture {
        return cityService.findBelongPrefectureByCityName(cityName)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "not found city")
    }

    @GetMapping("prefectures/nearby/{prefectureName}")
    fun nearPrefectureController(
        @PathVariable prefectureName: String
    ): List<Prefecture> {
        return prefectureService.findNearPrefectures(prefectureName)
    }

    @GetMapping("cities")
    fun citiesController(): List<ResponseCity>{
        return cityService.findAllCities()
    }

    @GetMapping("cities/by-prefecture/{prefectureName}")
    fun citiesByPrefectureController(
        @PathVariable prefectureName: String
    ): List<ResponseCity> {
        return cityService.findCitiesByPrefecture(prefectureName)
    }

    @GetMapping("cities/by-city/{cityName}")
    fun cityByCityNameController(
        @PathVariable cityName: String
    ): ResponseCity {
        return cityService.findCityByCityName(cityName)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "not found City")
    }

}