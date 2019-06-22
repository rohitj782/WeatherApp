package com.rohitrj.weatherapp.data.db.unitlocalized


interface UnitSpecificCurrentWeather{

    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}