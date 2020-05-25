package com.yahorb.mvvm.repository

import com.yahorb.mvvm.model.data.Term
import com.yahorb.mvvm.repository.data.geocode.Geocode
import com.yahorb.mvvm.repository.data.weather.Weather
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ITunesAPI {

/*    @GET("/geocode")
    @Headers("Content-type: application/json")
    fun geocode(@Query("address") address: String): Single<Geocode>

    @GET("/weather")
    @Headers("Content-type: application/json")
    fun weather(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("lang") lang: String
    ): Single<Weather>*/

    @GET("/search")
    fun search(@Query("term") term: String ): Observable<Term>
}
