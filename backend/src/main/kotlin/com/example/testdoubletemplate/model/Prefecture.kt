package com.example.testdoubletemplate.model

data class Prefecture(
    val prefectureName: String,
    val areaOfPrefecture: Double,
    val populationOfPrefecture: Int,
    val metropolitan: String
)

fun Prefecture.removeOriginalPrefecture(): Prefecture {
    return Prefecture(
        prefectureName = this.prefectureName.substringAfter("#"),
        areaOfPrefecture = this.areaOfPrefecture,
        populationOfPrefecture = this.populationOfPrefecture,
        metropolitan = this.metropolitan,
    )
}
