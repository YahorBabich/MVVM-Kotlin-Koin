package com.ruby.mvvm.repository

import io.reactivex.Completable
import io.reactivex.Single
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.repository.data.getDailyForecasts
import com.ruby.mvvm.repository.data.getLocation
import com.ruby.mvvm.repository.local.WeatherRepository

class WeatherRepositoryImpl(private val weatherDatasource: WeatherDatasource) : WeatherRepository {

    val weatherCache = arrayListOf<DailyForecastModel>()

    override fun getSelectedWeatherDetail(id: String) =
        Single.just(weatherCache.first { it.id == id })

    override fun searchWeather(location: String): Completable = weatherDatasource.geocode(location)
        .map { it.getLocation() ?: throw IllegalStateException("No Location data") }
        .flatMap { weatherDatasource.weather(it.lat, it.lng, "EN") }
        .map { it.getDailyForecasts() }
        .doOnSuccess { weatherCache.clear(); weatherCache.addAll(it) }
        .toCompletable()

    override fun getWeather(): Single<List<DailyForecastModel>> = Single.just(weatherCache)
}