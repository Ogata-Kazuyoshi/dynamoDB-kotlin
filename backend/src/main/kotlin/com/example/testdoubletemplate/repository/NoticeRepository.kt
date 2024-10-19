package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.Notice
import org.springframework.stereotype.Repository

interface NoticeRepository {
    fun findAllNoticeList(): List<Notice>
}

@Repository
class DefaultNoticeRepository(
    var dynamoDBRepository: NoSQLRepository<InformationTableEntity>
): NoticeRepository {
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