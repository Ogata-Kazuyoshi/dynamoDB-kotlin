package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.config.DynamoDBGenerator
import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.FAQ
import com.example.testdoubletemplate.repository.dynamoDB.BaseRepository
import org.springframework.stereotype.Repository


interface FAQRepository: BaseRepository {
    fun findAllFAQList(): List<FAQ>
}

@Repository
class DefaultFAQRepository(
    dynamoDBGenerator: DynamoDBGenerator,
): FAQRepository {
    override val dynamoDBRepository = dynamoDBGenerator.build<InformationTableEntity>()

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