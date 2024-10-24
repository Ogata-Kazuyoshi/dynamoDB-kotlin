package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.repository.NoSQLRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDBTableDefine(
    val dynamoDBTableGenerator: DynamoDBTableGenerator
) {
    @Bean
    fun dynamoDBRepositoryForInformation(
        @Value("\${spring.profiles.active}")
        environment: String
    ):NoSQLRepository<InformationTableEntity>{
        return createDynamoDBRepository("information_table_${environment}")
    }

    @Bean
    fun dynamoDBRepositoryForJapanTable(
        @Value("\${spring.profiles.active}")
        environment: String
    ):NoSQLRepository<JapanTableEntity>{
        return createDynamoDBRepository("japan_table_${environment}")
    }

    private inline fun <reified T> createDynamoDBRepository(
        tableName: String
    ): NoSQLRepository<T> {
        return dynamoDBTableGenerator.build(
            tableName = tableName,
            schema = T::class.java
        )
    }
}