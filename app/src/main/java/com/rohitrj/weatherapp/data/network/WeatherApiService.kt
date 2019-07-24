package com.rohitrj.weatherapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rohitrj.weatherapp.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap

const val API_KEY = "d33c0dbc82634eaca2f103532191906"

//required url
// http://api.apixu.com/v1/current.json?key=d33c0dbc82634eaca2f103532191906&q=tanakpur

interface WeatherApiService {

    @GET("current.json")    //end point url
    fun getCurrentWeather(
        @QueryMap map: HashMap<String,String>
    ): Deferred<CurrentWeatherResponse> //deferred is a part of kotlin coroutine


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnecetvityInterceptor
        ): WeatherApiService {

            //interceptor is used to insert key query parameter ...although we can insert it directly ...
            val requestInterceptor = Interceptor{chain ->
                 val url = chain.request()
                     .url()
                     .newBuilder()
                     .addQueryParameter("key", API_KEY) //adding api key
                     .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) //because we have used the deferred object.
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}