package com.rohitrj.weatherapp.data.repository

import androidx.lifecycle.LiveData
import com.rohitrj.weatherapp.data.db.CurrentWeatherDao
import com.rohitrj.weatherapp.data.db.unitlocalized.UnitSpecificCurrentWeather
import com.rohitrj.weatherapp.data.network.WeatherNetworkDataSource
import com.rohitrj.weatherapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*


class ForecastRepositoryImpl(
    private val cuurrentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource

) : ForecastRepository {
    init {
        weatherNetworkDataSource.dowloadedCurrentWeather.observeForever {
            presistFetchedCurrentWeather(it)
        }
    }

    //get data from the local repository
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeather> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if(metric) cuurrentWeatherDao.getMetricCurrentWeather()
            else cuurrentWeatherDao.getImperialCurrentWeather()
        }
    }

    private fun presistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch (Dispatchers.IO){
            cuurrentWeatherDao.upsert(fetchedWeather.currentWeatherEntry )
        }
    }

    //for network calls
    private suspend  fun initWeatherData(){
       if(isFetchNeeded(ZonedDateTime.now().minusDays(1))){
           fetchCurrentWeather()
       }
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Dehradun"
        , Locale.getDefault().language
        )
    }

    private fun isFetchNeeded(lastfetchTime:ZonedDateTime):Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastfetchTime.isBefore(thirtyMinutesAgo)
    }
}
