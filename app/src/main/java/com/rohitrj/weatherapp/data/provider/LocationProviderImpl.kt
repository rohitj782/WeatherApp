package com.rohitrj.weatherapp.data.provider

import com.rohitrj.weatherapp.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
    return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Dehradun"
    }
}