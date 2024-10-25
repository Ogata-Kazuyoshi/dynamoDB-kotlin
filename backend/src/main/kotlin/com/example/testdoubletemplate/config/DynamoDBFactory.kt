package com.example.testdoubletemplate.config

import com.example.testdoubletemplate.entity.TableEntity
import com.example.testdoubletemplate.repository.dynamoDB.DynamoDBRepository
import com.example.testdoubletemplate.repository.dynamoDB.NoSQLRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.TableSchema

interface DynamoDBFactory {
    fun <Table : TableEntity> build(tableClass: Class<Table>): NoSQLRepository<Table>

}


@Component
class DefaultDynamoDBFactory(
    @Value("\${spring.profiles.active}")
    val enrivonment: String,
    val dynamoDbClient: DynamoDbEnhancedClient,
): DynamoDBFactory {
    override fun <Table: TableEntity> build(tableClass: Class<Table>): NoSQLRepository<Table> {
        val instance = tableClass.getConstructor().newInstance()
        val dynamoDBTable = dynamoDbClient.table(
            "${instance.tableName}_$enrivonment",
            TableSchema.fromBean(tableClass)
        )
        return DynamoDBRepository(dynamoDBTable)
    }

}