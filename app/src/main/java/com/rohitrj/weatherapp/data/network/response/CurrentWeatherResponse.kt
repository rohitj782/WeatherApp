package com.rohitrj.weatherapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.rohitrj.weatherapp.data.db.entity.CurrentWeatherEntry
import com.rohitrj.weatherapp.data.db.entity.WeatherLocation


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    
    val location: WeatherLocation
)