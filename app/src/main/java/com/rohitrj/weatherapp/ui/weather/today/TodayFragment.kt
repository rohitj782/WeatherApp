package com.rohitrj.weatherapp.ui.weather.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rohitrj.weatherapp.R
import com.rohitrj.weatherapp.data.WeatherApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodayFragment : Fragment() {

    companion object {
        fun newInstance() = TodayFragment()
    }

    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TodayViewModel::class.java)


        val apiService =  WeatherApiInterface.invoke()
        GlobalScope.launch(Dispatchers.Main) {
            val response = apiService.getCurrentWeather(location = "Tanakpur").await()
            Log.i("MYTAG", response.toString())
        }

    }

}
