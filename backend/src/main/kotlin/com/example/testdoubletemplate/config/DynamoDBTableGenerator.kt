package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.repository.DynamoDBRepository
import com.example.testdoubletemplate.repository.NoSQLRepository
import org.springframework.stereotype.Component
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Component
class DynamoDBTableGenerator(
    private val dynamoDbClient: DynamoDbEnhancedClient,
) {
    fun <Table> build(
        tableName: String,
        schema: Class<Table>,
    ): NoSQLRepository<Table>{
        val dynamoDBTable = dynamoDbClient.table(
            tableName,
            TableSchema.fromBean(schema)
        )
        return DynamoDBRepository(dynamoDBTable)
    }
}