package com.rohitrj.weatherapp.ui.weather.today

import androidx.lifecycle.ViewModel;
import com.rohitrj.weatherapp.data.provider.UnitProvider
import com.rohitrj.weatherapp.data.repository.ForecastRepository
import com.rohitrj.weatherapp.internal.UnitSystem
import com.rohitrj.weatherapp.internal.lazyDeferred

class TodayViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
