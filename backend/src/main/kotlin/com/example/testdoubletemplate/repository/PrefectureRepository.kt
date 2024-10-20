package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.entity.toPrefecture
import com.example.testdoubletemplate.model.Prefecture
import org.springframework.stereotype.Repository

interface PrefectureRepository {
    fun findAllPrefectures(): List<Prefecture>
    fun findPrefecture(sk: String): Prefecture?
    fun findNearPrefectures(prefectureName: String): List<Prefecture>
}

@Repository
class DefaultPrefectureRepository(
    val dynamoDBRepository: NoSQLRepository<JapanTableEntity>
): PrefectureRepository{
    override fun findAllPrefectures(): List<Prefecture> {
        return dynamoDBRepository.findAllByPK("Prefecture")
            .map { it.toPrefecture()}
    }

    override fun findPrefecture(sk: String): Prefecture? {
        val item = dynamoDBRepository.findItemByPKandSK("Prefecture",sk)
        return item?.toPrefecture()
    }

    override fun findNearPrefectures(prefectureName: String): List<Prefecture> {
        return dynamoDBRepository.findAllByPKandSKBegin(
            pk = "NextToPrefecture",
            skBegin = "$prefectureName#"
        ).map { it.toPrefecture()}
    }

}