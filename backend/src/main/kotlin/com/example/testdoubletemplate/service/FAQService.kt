package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.FAQ
import com.example.testdoubletemplate.repository.FAQRepository
import org.springframework.stereotype.Service

interface FAQService {
    fun findAllFAQList(): List<FAQ>
}

@Service
class DefaultFAQService(
    val faqRepository: FAQRepository
): FAQService {
    override fun findAllFAQList(): List<FAQ> {
        return faqRepository.findAllFAQList()
    }
}