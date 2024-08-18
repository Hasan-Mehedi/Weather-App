package com.mehedi.weather.model

data class Forecast(
    val date: String,
    val maxTemp: String,
    val minTemp: String,
    val sunrise: String,
    val sunset: String,
    val icon: String,
    val conditionText: String,
    val hour: List<Hour>,
)
