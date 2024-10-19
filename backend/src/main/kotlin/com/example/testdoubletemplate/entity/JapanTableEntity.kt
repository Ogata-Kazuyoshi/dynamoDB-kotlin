package com.example.testdoubletemplate.entity

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey

@DynamoDbBean
data class JapanTableEntity(
    @get:DynamoDbPartitionKey
    var category: String = "",
    @get:DynamoDbSortKey
    var mainSk: String = "",

    @get:DynamoDbSecondaryPartitionKey(indexNames = ["BelongPrefectureGSI"])
    var belongPrefecture: String = "",

    @get:DynamoDbSecondarySortKey(indexNames = ["AreaOfPrefectureLSI"])
    var areaOfPrefecture: Double = 0.0,
    @get:DynamoDbSecondarySortKey(indexNames = ["PopulationOfPrefectureLSI"])
    var populationOfPrefecture: Int = 0,
    var metropolitan: String = "",
    var areaOfCity: Double = 0.0,
    var populationOfCity: Int = 0,
)
