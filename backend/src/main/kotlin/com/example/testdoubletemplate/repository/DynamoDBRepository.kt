package com.example.testdoubletemplate.repository

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest
import java.util.stream.Collectors

interface NoSQLRepository<Table> {
    fun findAllByPK(pk: String): List<Table>
}

class DynamoDBRepository<Table> (
    private val dynamoDBTable: DynamoDbTable<Table>
): NoSQLRepository<Table> {
    override fun findAllByPK(pk: String): List<Table> {
        val queryConditional = QueryConditional
            .keyEqualTo(
                Key.builder()
                    .partitionValue(pk)
                    .build()
            )

        val request = QueryEnhancedRequest.builder()
            .queryConditional(queryConditional)
            .build()

        return dynamoDBTable
            .query(request)
            .stream()
            .flatMap { page -> page.items().stream() }
            .collect(Collectors.toList())
    }
}