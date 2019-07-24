package com.rohitrj.weatherapp.ui.weather.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rohitrj.weatherapp.R
import com.rohitrj.weatherapp.ui.base.ScopeFragment
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


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
            Log.i("MYTAG", it.toString())
        })
    }

}

