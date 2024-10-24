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
    var areaOfPrefecture: Double? = null,
    @get:DynamoDbSecondarySortKey(indexNames = ["PopulationOfPrefectureLSI"])
    var populationOfPrefecture: Int? = null,
    var metropolitan: String? = null,
    var areaOfCity: Double? = null,
    var populationOfCity: Int? = null,
): TableEntity {
    override val tableName: String
        get() = "japan_table"

}

fun JapanTableEntity.toPrefecture(): Prefecture {
    return Prefecture(
        prefectureName = this.mainSk,
        areaOfPrefecture = this.areaOfPrefecture?: 0.0,
        populationOfPrefecture = this.populationOfPrefecture?: 0,
        metropolitan = this.metropolitan?: "",
    )
}

fun JapanTableEntity.toCity(): City {
    return City(
        cityName = this.mainSk,
        belongPrefecture = this.belongPrefecture?: "",
        areaOfPrefecture = this.areaOfPrefecture?: 0.0,
        populationOfPrefecture = this.populationOfPrefecture?: 0,
        metropolitan = this.metropolitan?: "",
        areaOfCity = this.areaOfCity?: 0.0,
        populationOfCity = this.populationOfCity?: 0,
    )
}
