package com.rohitrj.weatherapp.data

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

interface WeatherApiInterface {

    @GET("current.json")
    fun getCurrentWeather(@QueryMap map: HashMap<String,String>): Deferred<CurrentWeatherResponse> //deferred is a part of kotlin coroutine


    companion object {
        operator fun invoke():WeatherApiInterface{

            //interceptor is used to insert key query parameter ...although we can insert it directly ...
            val requestInterceptor = Interceptor{chain ->
                 val url = chain.request()
                     .url()
                     .newBuilder()
                     .addQueryParameter("key", API_KEY)
                     .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request )
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return  Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) //bcoz we have used the deferred object instead of call.
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiInterface::class.java)
        }
    }
}