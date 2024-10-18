package com.example.testdoubletemplate.repository

import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.model.FAQ
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository


interface FAQRepository {
    fun findAllFAQList(): List<FAQ>
}

@Repository
class DefaultFAQRepository(
    @Qualifier("dynamoDBRepositoryForInformation")
    private val dynamoDBRepository: NoSQLRepository<InformationTableEntity>
): FAQRepository {
    override fun findAllFAQList(): List<FAQ> {
        return dynamoDBRepository
            .findAllByPK("FAQ")
            .map { FAQ(it.answer, it.question) }
    }

}