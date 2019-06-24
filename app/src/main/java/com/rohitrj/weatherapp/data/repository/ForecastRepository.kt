package com.rohitrj.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.rohitrj.weatherapp.data.db.unitlocalized.UnitSpecificCurrentWeather

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeather>
}