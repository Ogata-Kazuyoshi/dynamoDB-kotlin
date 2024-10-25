package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.DynamoDBFactory
import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.Notice
import com.example.testdoubletemplate.repository.dynamoDB.BaseRepository
import org.springframework.stereotype.Repository

interface NoticeRepository: BaseRepository {
    fun findAllNoticeList(): List<Notice>
}

@Repository
class DefaultNoticeRepository(
    dynamoDBFactory: DynamoDBFactory,
): NoticeRepository {
    override val dynamoDBRepository = dynamoDBFactory.build(InformationTableEntity::class.java)

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