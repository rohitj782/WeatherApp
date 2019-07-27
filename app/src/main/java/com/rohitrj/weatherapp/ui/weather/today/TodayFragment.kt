package com.rohitrj.weatherapp.ui.weather.today

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.rohitrj.weatherapp.R
import com.rohitrj.weatherapp.internal.glide.GlideApp
import com.rohitrj.weatherapp.ui.base.ScopeFragment
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import org.threeten.bp.ZonedDateTime
import java.util.concurrent.locks.Condition


class TodayFragment : ScopeFragment(),KodeinAware {

    override val kodein by  closestKodein()
    private val viewModelFactory:TodayViewmodelFactory by instance()

    private lateinit var viewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodayViewModel::class.java)

        bindUI()

    }

    private fun bindUI()= launch{
        val currentweather  = viewModel.weather.await()
        currentweather.observe(this@TodayFragment, Observer {
            if(it==null) return@Observer

            groupLoading.visibility = View.INVISIBLE
            updateLocation("Dehradun")
            updateTemp(it.temperature,it.feelsLikeTemperature)
            updatePrep(it.precipitationVolume)
            updateVisibility(it.visibilityDistance)
            updateWind(it.windDirection,it.windSpeed)
            updateCondition(it.conditionText)

            GlideApp.with(this@TodayFragment)
                    .load("http:${it.conditionIconUrl}")
                    .into(imageViewIcon)
        })
    }

    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }
    @SuppressLint("SetTextI18n")
    private fun updateTemp(temp:Double, feelsLike:Double){
        val unitAbbreviation = if(viewModel.isMetric)"°C" else "°F"
        textViewTemperature.text = "$temp$unitAbbreviation"
        textViewFeelsLike.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String){
        textViewCondition.text = condition
    }

    @SuppressLint("SetTextI18n")
    private fun updatePrep(prep:Double){
        val unitAbbreviation = if(viewModel.isMetric)"°mm" else "°in"
        textViewPrecipitation.text = "Precipitation: $prep $unitAbbreviation "
    }
    @SuppressLint("SetTextI18n")
    private fun updateWind(direction: String, speed: Double){
        val unitAbbreviation = if(viewModel.isMetric)"°kph" else "°mph"
        textViewWindCondition.text = "Wind: $direction $speed $unitAbbreviation"
    }

    @SuppressLint("SetTextI18n")
    private fun updateVisibility(visibility: Double){
        val unitAbbreviation = if(viewModel.isMetric)"°km" else "°mi"
        textViewVisibility.text = "Wind: $visibility $unitAbbreviation"
    }

}

