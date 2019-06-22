package com.rohitrj.weatherapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rohitrj.weatherapp.data.db.entity.CURRENT_WEATHER_ID
import com.rohitrj.weatherapp.data.db.entity.CurrentWeatherEntry
import com.rohitrj.weatherapp.data.db.unitlocalized.ImperialCurrentWeather
import com.rohitrj.weatherapp.data.db.unitlocalized.MetricCurrentWeather

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)    //as all instance will have the same id
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("Select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getMetricCurrentWeather():LiveData<MetricCurrentWeather>

    @Query("Select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getImperialCurrentWeather():LiveData<ImperialCurrentWeather>

}