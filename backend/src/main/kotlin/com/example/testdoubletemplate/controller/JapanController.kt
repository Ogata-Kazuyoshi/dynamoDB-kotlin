package com.example.testdoubletemplate.controller

import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.service.PrefectureService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/japanInformation")
class JapanController(
    val prefectureService: PrefectureService
) {
    @GetMapping("/prefectures")
    fun prefecturesController(): List<Prefecture> {
        return prefectureService.findAllPrefectures()
    }

    @GetMapping("/prefectures/{prefectureName}")
    fun prefectureController(
        @PathVariable prefectureName: String
    ): Prefecture {
        return prefectureService.findPrefecture(prefectureName)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "not found prefecture")
    }

}