package com.rohitrj.weatherapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rohitrj.weatherapp.data.network.response.CurrentWeatherResponse
import com.rohitrj.weatherapp.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixuService: WeatherApiService
) : WeatherNetworkDataSource {

    private val _dowloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val dowloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _dowloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, lang: String ) {

        try {
            val filter: HashMap<String, String> = HashMap()
            filter["q"] = location
            filter["lang"] = lang
            val fetchCurrentWeather = apixuService.getCurrentWeather(filter)
                .await()
            _dowloadedCurrentWeather.postValue(fetchCurrentWeather)
        }catch (e:NoConnectivityException){
            Log.e("connectivity", "No Internet connection ",e)
        }

    }
}