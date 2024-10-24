package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.repository.DynamoDBRepository
import com.example.testdoubletemplate.repository.NoSQLRepository
import org.springframework.stereotype.Component
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Component
class DynamoDBTableGenerator(
    val dynamoDbClient: DynamoDbEnhancedClient,
) {
    final inline fun <reified Table> build(
        tableName: String,
    ): NoSQLRepository<Table>{
        val dynamoDBTable = dynamoDbClient.table(
            tableName,
            TableSchema.fromBean(Table::class.java)
        )
        return DynamoDBRepository(dynamoDBTable)
    }
}