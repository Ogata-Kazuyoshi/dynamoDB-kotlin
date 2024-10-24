package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.DynamoDBGenerator
import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.FAQ
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository


interface FAQRepository {
    fun findAllFAQList(): List<FAQ>
}

@Repository
class DefaultFAQRepository(
    dynamoDBGenerator: DynamoDBGenerator,
    @Value("\${spring.profiles.active}")
    private val environment: String
): FAQRepository {
    private val dynamoDBRepository: NoSQLRepository<InformationTableEntity> =
        dynamoDBGenerator.build("information_table_${environment}")

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