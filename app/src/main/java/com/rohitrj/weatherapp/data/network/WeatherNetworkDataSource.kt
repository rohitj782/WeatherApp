package com.rohitrj.weatherapp.data.network

import androidx.lifecycle.LiveData
import com.rohitrj.weatherapp.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {

    val dowloadedCurrentWeather: LiveData <CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location : String,
        lang: String
    )
}