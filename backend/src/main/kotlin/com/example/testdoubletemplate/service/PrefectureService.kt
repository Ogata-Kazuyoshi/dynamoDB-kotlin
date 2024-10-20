package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.model.removeOriginalPrefecture
import com.example.testdoubletemplate.repository.PrefectureRepository
import org.springframework.stereotype.Service

interface PrefectureService {
    fun findAllPrefectures(): List<Prefecture>
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

    override fun findPrefecture(sk: String): Prefecture? {
        return prefectureRepository.findPrefecture(sk)
    }

    override fun findNearPrefectures(prefectureName: String): List<Prefecture> {
        return prefectureRepository.findNearPrefectures(prefectureName)
            .map { it.removeOriginalPrefecture() }

    }

}