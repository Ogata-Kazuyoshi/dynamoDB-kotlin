package com.example.testdoubletemplate.service

import com.example.testdoubletemplate.model.FAQ
import com.example.testdoubletemplate.model.QuestionAndAnswer
import com.example.testdoubletemplate.model.ResponseFAQ
import com.example.testdoubletemplate.repository.FAQRepository
import org.springframework.stereotype.Service

interface FAQService {
    fun findAllFAQList(): List<ResponseFAQ>
}

@Service
class DefaultFAQService(
    val faqRepository: FAQRepository
): FAQService {
    override fun findAllFAQList(): List<ResponseFAQ> {
        val FAQItems = faqRepository.findAllFAQList()
        val result: MutableList<ResponseFAQ> = mutableListOf()
        result.add(createResponseFAQ("3D表示", FAQItems))
        result.add(createResponseFAQ("データ準備", FAQItems))
        result.add(createResponseFAQ("その他", FAQItems))
        return result
    }

    private fun createResponseFAQ(category: String, FAQItems: List<FAQ>): ResponseFAQ {
        val filteredItems = FAQItems.filter { it.faqCategoryName == category }
        return ResponseFAQ(
            category = category,
            questionAndAnswerList = filteredItems.sortedByDescending { it.questionCreateAt }
                .map { QuestionAndAnswer(
                    question = it.question,
                    answer = it.answer,
                    answerCreateAt = it.answerCreateAt,
                    questionCreateAt = it.questionCreateAt
                ) }
        )
    }
}