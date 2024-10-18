package com.example.testdoubletemplate.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey

@DynamoDbBean
data class InformationTableEntity(
    @get:DynamoDbPartitionKey
    var category: String = "",
    @get:DynamoDbSortKey
    var id: Int = 0,

    var faqCategoryName: String = "",
    var answer: String = "",
    var question: String = "",
    var createAtAnswer: String = "",
    var createAtQuestion: String = "",
)