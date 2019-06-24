package com.rohitrj.weatherapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.rohitrj.weatherapp.internal.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

class ConnecetvityInterceptorImpl (context: Context): ConnecetvityInterceptor {

    private val appContext = context.applicationContext

    //if connection is available then proceed otherwise throw exception
    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isOnLine()){
            throw NoConnectivityException()
        }
        return chain.proceed(chain.request())
    }

    private fun isOnLine():Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo!=null && networkInfo.isConnected
    }
}