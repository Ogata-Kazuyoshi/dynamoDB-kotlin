package com.example.testdoubletemplate.entity

import com.example.testdoubletemplate.model.City
import com.example.testdoubletemplate.model.Prefecture
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
    var belongPrefecture: String? = null ,

    @get:DynamoDbSecondarySortKey(indexNames = ["AreaOfPrefectureLSI"])
    var areaOfPrefecture: Double = 0.0,
    @get:DynamoDbSecondarySortKey(indexNames = ["PopulationOfPrefectureLSI"])
    var populationOfPrefecture: Int = 0,
    var metropolitan: String = "",
    var areaOfCity: Double? = null,
    var populationOfCity: Int? = null,
)

fun JapanTableEntity.toPrefecture(): Prefecture {
    return Prefecture(
        prefectureName = this.mainSk,
        areaOfPrefecture = this.areaOfPrefecture,
        populationOfPrefecture = this.populationOfPrefecture,
        metropolitan = this.metropolitan,
    )
}

fun JapanTableEntity.toCity(): City {
    return City(
        cityName = this.mainSk,
        belongPrefecture = this.belongPrefecture!!,
        areaOfPrefecture = this.areaOfPrefecture,
        populationOfPrefecture = this.populationOfPrefecture,
        metropolitan = this.metropolitan,
        areaOfCity = this.areaOfCity!!,
        populationOfCity = this.populationOfCity!!,
    )
}
