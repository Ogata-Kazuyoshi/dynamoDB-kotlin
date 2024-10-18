package com.example.testdoubletemplate.controller

import com.example.testdoubletemplate.model.FAQ
import com.example.testdoubletemplate.service.FAQService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/information")
class InformationController(
    val faqService: FAQService
) {
    @GetMapping("/faq")
    fun faqController(): List<FAQ> {
        return faqService.findAllFAQList()
    }
}