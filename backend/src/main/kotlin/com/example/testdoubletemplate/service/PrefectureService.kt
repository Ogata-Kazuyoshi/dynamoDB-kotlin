package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.model.removeOriginalPrefecture
import com.example.testdoubletemplate.model.request.RequestPrefecture
import com.example.testdoubletemplate.repository.PrefectureRepository
import org.springframework.stereotype.Service

interface PrefectureService {
    fun findAllPrefectures(): List<Prefecture>
    fun putPrefecture(reqBody: RequestPrefecture)
    fun findPrefecturesByAreaSize(): List<Prefecture>
    fun findPrefecturesByPopulation(): List<Prefecture>
    fun findPrefecture(sk: String): Prefecture?
    fun findNearPrefectures(prefectureName: String): List<Prefecture>

}

@Service
class DefaultPrefectureService(
    val prefectureRepository: PrefectureRepository
): PrefectureService {
    override fun findAllPrefectures(): List<Prefecture> {
        return prefectureRepository.findAllPrefectures()
    }

    override fun putPrefecture(reqBody: RequestPrefecture) {
        prefectureRepository.putPrefecture(JapanTableEntity(
            category = "Prefecture",
            mainSk = reqBody.prefectureName,
            areaOfPrefecture = reqBody.areaOfPrefecture,
            populationOfPrefecture = reqBody.populationOfPrefecture,
            metropolitan = reqBody.metropolitan,
        ))
    }

    override fun findPrefecturesByAreaSize(): List<Prefecture> {
        return prefectureRepository.findPrefecturesByAreaSize()
    }

    override fun findPrefecturesByPopulation(): List<Prefecture> {
        return prefectureRepository.findPrefecturesByPopulation()
    }

    override fun findPrefecture(sk: String): Prefecture? {
        return prefectureRepository.findPrefecture(sk)
    }

    override fun findNearPrefectures(prefectureName: String): List<Prefecture> {
        return prefectureRepository.findNearPrefectures(prefectureName)
            .map { it.removeOriginalPrefecture() }

    }

}