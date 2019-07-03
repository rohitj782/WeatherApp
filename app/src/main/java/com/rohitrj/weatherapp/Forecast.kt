package com.rohitrj.weatherapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.rohitrj.weatherapp.data.db.CurrentDatabase
import com.rohitrj.weatherapp.data.db.entity.CurrentWeatherEntry
import com.rohitrj.weatherapp.data.network.*
import com.rohitrj.weatherapp.data.repository.ForecastRepository
import com.rohitrj.weatherapp.data.repository.ForecastRepositoryImpl
import com.rohitrj.weatherapp.ui.weather.today.TodayViewmodelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Forecast : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@Forecast))

        bind() from singleton { CurrentDatabase(instance()) }
        bind() from singleton { instance<CurrentDatabase>().currentWeatherDao() }
        bind<ConnecetvityInterceptor>() with singleton { ConnecetvityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiInterface(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance()) }
        bind() from provider { TodayViewmodelFactory(instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}