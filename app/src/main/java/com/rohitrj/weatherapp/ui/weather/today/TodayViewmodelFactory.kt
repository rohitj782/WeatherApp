package com.rohitrj.weatherapp.ui.weather.today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rohitrj.weatherapp.data.repository.ForecastRepository


class TodayViewmodelFactory(private val forecastRepository: ForecastRepository ):
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayViewModel(forecastRepository)as T
    }
}