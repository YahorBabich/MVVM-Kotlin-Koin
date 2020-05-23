package com.yahorb.mvvm.repository.local

import com.yahorb.mvvm.repository.data.geocode.Geocode
import com.yahorb.mvvm.repository.data.geocode.Location
import com.yahorb.mvvm.repository.data.weather.Weather

/**
 * Json reader
 */
interface JsonReader {
    fun getAllLocations(): Map<Location, String>
    fun getWeather(name: String): Weather
    fun getGeocode(name: String): Geocode
}