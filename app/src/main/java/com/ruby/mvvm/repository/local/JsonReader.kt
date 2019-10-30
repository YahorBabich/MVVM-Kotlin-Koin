package com.ruby.mvvm.repository.local

import com.ruby.mvvm.repository.data.geocode.Geocode
import com.ruby.mvvm.repository.data.geocode.Location
import com.ruby.mvvm.repository.data.weather.Weather

/**
 * Json reader
 */
interface JsonReader {
    fun getAllLocations(): Map<Location, String>
    fun getWeather(name: String): Weather
    fun getGeocode(name: String): Geocode
}