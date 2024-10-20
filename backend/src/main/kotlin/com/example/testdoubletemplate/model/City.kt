package com.example.testdoubletemplate.model

data class City(
    val cityName: String,
    val belongPrefecture: String,
    val areaOfPrefecture: Double,
    val populationOfPrefecture: Int,
    val metropolitan: String,
    val areaOfCity: Double,
    val populationOfCity: Int
)

fun City.toResponseCity(): ResponseCity {
    return ResponseCity(
        cityName = this.cityName,
        areaOfCity = this.areaOfCity,
        populationOfCity = this.populationOfCity,
    )
}

fun City.toPrefecture(): Prefecture {
    return Prefecture(
        prefectureName = this.belongPrefecture,
        areaOfPrefecture = this.areaOfPrefecture,
        populationOfPrefecture = this.populationOfPrefecture,
        metropolitan = this.metropolitan,
    )
}
