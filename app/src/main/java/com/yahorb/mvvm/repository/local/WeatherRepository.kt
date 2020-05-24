package com.yahorb.mvvm.repository.local

import com.yahorb.mvvm.model.data.DailyForecastModel
import io.reactivex.disposables.Disposable

interface WeatherRepository {
    fun searchWeather(
        location: String,
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable

    fun getSelectedWeatherDetail(
        id: String,
        onSuccess: (DailyForecastModel) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable

    fun getWeather(
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable
}