package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.DynamoDBGenerator
import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.Notice
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

interface NoticeRepository {
    fun findAllNoticeList(): List<Notice>
}

@Repository
class DefaultNoticeRepository(
    dynamoDBGenerator: DynamoDBGenerator,
    @Value("\${spring.profiles.active}")
    private val environment: String
): NoticeRepository {
    private val dynamoDBRepository: NoSQLRepository<InformationTableEntity> =
        dynamoDBGenerator.build("information_table_${environment}")

    override fun findAllNoticeList(): List<Notice> {
        return dynamoDBRepository
            .findAllByPK("NOTICE")
            .map { Notice(
                noticeTitle = it.noticeTitle,
                noticeTag = it.noticeTag,
                noticeDescription = it.noticeDescription,
                noticeCreateAt = it.noticeCreateAt
            ) }
    }

}