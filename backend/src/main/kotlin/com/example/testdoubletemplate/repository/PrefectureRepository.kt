package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.DynamoDBGenerator
import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.entity.toPrefecture
import com.example.testdoubletemplate.model.Prefecture
import com.example.testdoubletemplate.repository.dynamoDB.BaseRepository
import org.springframework.stereotype.Repository

interface PrefectureRepository: BaseRepository {
    fun findAllPrefectures(): List<Prefecture>
    fun putPrefecture(item: JapanTableEntity)
    fun findPrefecturesByAreaSize(): List<Prefecture>
    fun findPrefecturesByPopulation(): List<Prefecture>
    fun findPrefecture(sk: String): Prefecture?
    fun findNearPrefectures(prefectureName: String): List<Prefecture>
}

@Repository
class DefaultPrefectureRepository(
    dynamoDBGenerator: DynamoDBGenerator,
): PrefectureRepository{
    override val dynamoDBRepository = dynamoDBGenerator.build<JapanTableEntity>()
    override fun findAllPrefectures(): List<Prefecture> {
        return dynamoDBRepository.findAllByPK("Prefecture")
            .map { it.toPrefecture()}
    }

    override fun putPrefecture(item: JapanTableEntity) {
        dynamoDBRepository.putItem(item)
    }

    override fun findPrefecturesByAreaSize(): List<Prefecture> {
        return dynamoDBRepository.findAllByPKInLSI(
            "AreaOfPrefectureLSI",
            "Prefecture"
        ).map { it.toPrefecture() }
    }

    override fun findPrefecturesByPopulation(): List<Prefecture> {
        return dynamoDBRepository.findAllByPKInLSI(
            "PopulationOfPrefectureLSI",
            "Prefecture"
        ).map { it.toPrefecture() }
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