package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.FAQ
import org.springframework.stereotype.Repository


interface FAQRepository {
    fun findAllFAQList(): List<FAQ>
}

@Repository
class DefaultFAQRepository(
    val dynamoDBRepository: NoSQLRepository<InformationTableEntity>
): FAQRepository {
    override fun findAllFAQList(): List<FAQ> {
        return dynamoDBRepository
            .findAllByPK("FAQ")
            .map { FAQ(
                faqCategoryName = it.faqCategoryName,
                answer = it.answer,
                question = it.question,
                answerCreateAt = it.answerCreateAt,
                questionCreateAt = it.questionCreateAt
            ) }
    }

}