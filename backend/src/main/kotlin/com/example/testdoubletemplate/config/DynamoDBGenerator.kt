package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.entity.TableEntity
import com.example.testdoubletemplate.repository.dynamoDB.DynamoDBRepository
import com.example.testdoubletemplate.repository.dynamoDB.NoSQLRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

@Component
class DynamoDBGenerator(
    @Value("\${spring.profiles.active}")
    val enrivonment: String,
    val dynamoDbClient: DynamoDbEnhancedClient,
) {
    final inline fun <reified Table: TableEntity> build(): NoSQLRepository<Table> {
        val instance = Table::class.java.getConstructor().newInstance()
        val dynamoDBTable = dynamoDbClient.table(
            "${instance.tableName}_$enrivonment",
            TableSchema.fromBean(Table::class.java)
        )
        return DynamoDBRepository(dynamoDBTable)
    }
}