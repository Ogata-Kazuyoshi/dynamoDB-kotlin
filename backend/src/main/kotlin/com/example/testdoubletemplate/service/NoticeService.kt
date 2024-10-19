package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.Notice
import com.example.testdoubletemplate.repository.NoticeRepository
import org.springframework.stereotype.Service

interface NoticeService {
    fun findAllNotices():List<Notice>
}

@Service
class DefaultNoticeService(
    val noticeRepository: NoticeRepository
): NoticeService {
    override fun findAllNotices(): List<Notice> {
        return noticeRepository.findAllNoticeList().sortedByDescending { item ->
            item.noticeCreateAt
        }
    }

}