package com.ruby.mvvm.repository

import io.reactivex.Single
import com.ruby.mvvm.repository.data.geocode.Geocode
import com.ruby.mvvm.repository.data.weather.Weather
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherDatasource {

    @GET("/geocode")
    @Headers("Content-type: application/json")
    fun geocode(@Query("address") address: String): Single<Geocode>

    @GET("/weather")
    @Headers("Content-type: application/json")
    fun weather(@Query("lat") lat: Double?, @Query("lon") lon: Double?, @Query("lang") lang: String): Single<Weather>
}
