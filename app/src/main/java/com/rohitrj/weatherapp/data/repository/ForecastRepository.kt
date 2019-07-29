package com.rohitrj.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.rohitrj.weatherapp.data.db.entity.WeatherLocation
import com.rohitrj.weatherapp.data.db.unitlocalized.UnitSpecificCurrentWeather
import java.util.*

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeather>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}