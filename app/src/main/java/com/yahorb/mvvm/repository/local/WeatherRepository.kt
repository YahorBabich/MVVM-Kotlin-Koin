package com.yahorb.mvvm.repository.local

import com.yahorb.mvvm.model.data.DailyForecastModel
import io.reactivex.Completable
import io.reactivex.Single

interface WeatherRepository {
    fun searchWeather(location: String): Completable
    fun getWeather(): Single<List<DailyForecastModel>>
    fun getSelectedWeatherDetail(id: String): Single<DailyForecastModel>
}