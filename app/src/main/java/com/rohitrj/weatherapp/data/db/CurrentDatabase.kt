package com.rohitrj.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rohitrj.weatherapp.data.db.entity.CurrentWeatherEntry
import com.rohitrj.weatherapp.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class,WeatherLocation::class],
    version = 1
)

abstract class CurrentDatabase: RoomDatabase() {

    abstract fun currentWeatherDao():CurrentWeatherDao
    abstract fun weatherLocationDao():WeatherLocationDao

    companion object {
        @Volatile private var instance: CurrentDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext
                ,CurrentDatabase::class.java,"weatherdatabase")
                .build()

    }


}