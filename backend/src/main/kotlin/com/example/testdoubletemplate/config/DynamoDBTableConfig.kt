package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.entity.JapanTableEntity
import com.example.testdoubletemplate.repository.DynamoDBRepository
import com.example.testdoubletemplate.repository.NoSQLRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Configuration
class DynamoDBTableConfig {
    @Bean
    fun dynamoDBRepositoryForInformation(
        dynamoDbClient: DynamoDbEnhancedClient,
        @Value("\${spring.profiles.active}")
        environment: String
    ): NoSQLRepository<InformationTableEntity> {
        val dynamoDBTable = dynamoDbClient.table(
            "information_table_${environment}",
            TableSchema.fromBean(InformationTableEntity::class.java)
        )
        return DynamoDBRepository(dynamoDBTable)
    }

    @Bean
    fun dynamoDBRepositoryForJapanTable(
        dynamoDbClient: DynamoDbEnhancedClient,
        @Value("\${spring.profiles.active}")
        environment: String
    ): NoSQLRepository<JapanTableEntity> {
        val dynamoDBTable = dynamoDbClient.table(
            "japan_table_${environment}",
            TableSchema.fromBean(JapanTableEntity::class.java)
        )
        return DynamoDBRepository(dynamoDBTable)
    }
}