package com.example.testdoubletemplate.controller

import com.example.testdoubletemplate.model.Notice
import com.example.testdoubletemplate.model.ResponseFAQ
import com.example.testdoubletemplate.service.FAQService
import com.example.testdoubletemplate.service.NoticeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/information")
class InformationController(
    val faqService: FAQService,
    val noticeService: NoticeService
) {
    @GetMapping("/faq")
    fun faqController(): List<ResponseFAQ> {
        return faqService.findAllFAQList()
    }

    @GetMapping("/notice")
    fun noticeController(): List<Notice> {
        return noticeService.findAllNotices()
    }
}