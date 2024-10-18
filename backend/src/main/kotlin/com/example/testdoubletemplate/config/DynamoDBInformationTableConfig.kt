package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.entity.InformationTableEntity
import com.example.testdoubletemplate.repository.DynamoDBRepository
import com.example.testdoubletemplate.repository.NoSQLRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Configuration
class DynamoDBInformationTableConfig {
    @Bean
    fun dynamoDBRepositoryForInformation(
        dynamoDbClient: DynamoDbEnhancedClient,
        @Qualifier("dynamoDBTableForInformation")
        dynamoDBTable: DynamoDbTable<InformationTableEntity>
    ): NoSQLRepository<InformationTableEntity> {
        return DynamoDBRepository(dynamoDBTable)
    }

    @Bean
    fun dynamoDBTableForInformation(
        dynamoDBClient: DynamoDbEnhancedClient,
        @Value("\${spring.profiles.active}")
        environment: String
    ): DynamoDbTable<InformationTableEntity> {
        return dynamoDBClient
            .table(
                "information_table_${environment}",
                TableSchema.fromBean(InformationTableEntity::class.java)
            )
    }
}