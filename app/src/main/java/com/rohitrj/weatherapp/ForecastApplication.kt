package com.rohitrj.weatherapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.rohitrj.weatherapp.data.db.CurrentDatabase
import com.rohitrj.weatherapp.data.network.*
import com.rohitrj.weatherapp.data.provider.LocationProvider
import com.rohitrj.weatherapp.data.provider.LocationProviderImpl
import com.rohitrj.weatherapp.data.provider.UnitProvider
import com.rohitrj.weatherapp.data.provider.UnitProviderImpl
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

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { CurrentDatabase(instance()) }
        bind() from singleton { instance<CurrentDatabase>().currentWeatherDao() }
        bind() from singleton { instance<CurrentDatabase>().weatherLocationDao() }
        bind<ConnecetvityInterceptor>() with singleton { ConnecetvityInterceptorImpl(instance()) }
        bind() from singleton { WeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance()
                ,instance(),instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { TodayViewmodelFactory(instance(), instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}