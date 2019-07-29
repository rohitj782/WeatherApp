package com.rohitrj.weatherapp.data.provider

import com.rohitrj.weatherapp.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}