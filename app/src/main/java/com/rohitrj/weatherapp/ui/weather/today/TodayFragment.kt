package com.rohitrj.weatherapp.ui.weather.today

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rohitrj.weatherapp.R

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
        // TODO: Use the ViewModel
    }

}
