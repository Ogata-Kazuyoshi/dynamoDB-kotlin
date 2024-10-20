package com.example.testdoubletemplate.repository

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional

interface NoSQLRepository<Table> {
    fun findAllByPK(pk: String): List<Table>
    fun findItemByPKandSK(pk: String, sk: String): Table?
    fun findAllByPKandSKBegin(pk: String, skBegin: String): List<Table>
    fun findAllByGSI(indexName: String, pk: String): List<Table>
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

        return dynamoDBQueryForOriginalTable(queryConditional)
    }

    override fun findItemByPKandSK(pk: String, sk: String): Table? {
        val key = Key.builder()
            .partitionValue(pk)
            .sortValue(sk)
            .build()

        return try {
            dynamoDBTable.getItem(key)
        } catch (e: Exception) {
            null
        }
    }

    override fun findAllByPKandSKBegin(pk: String, skBegin: String): List<Table> {
        val queryConditional = QueryConditional
            .sortBeginsWith(
                Key.builder()
                    .partitionValue(pk)
                    .sortValue(skBegin)
                    .build()
            )

        return dynamoDBQueryForOriginalTable(queryConditional)
    }

    override fun findAllByGSI(indexName: String, pk: String): List<Table> {
        val queryConditional = QueryConditional
            .keyEqualTo(
                Key.builder()
                    .partitionValue(pk)
                    .build()
            )

        return dynamoDBQueryForSecondaryTable(indexName, queryConditional)
    }

    private fun dynamoDBQueryForOriginalTable(
        queryConditional: QueryConditional?
    ) = dynamoDBTable
        .query(queryConditional)
        .flatMap { it.items() }

    private fun dynamoDBQueryForSecondaryTable(
        indexName: String,
        queryConditional: QueryConditional?
    ) = dynamoDBTable
        .index(indexName)
        .query(queryConditional)
        .flatMap { it.items() }
}