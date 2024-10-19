package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.repository.PrefectureRepository
import org.springframework.stereotype.Service

interface PrefectureService {
    fun findAllPrefectures(): List<Prefecture>
    fun findPrefecture(sk: String): Prefecture?
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

}