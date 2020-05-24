package com.yahorb.mvvm.repository

import com.yahorb.mvvm.extension.with
import com.yahorb.mvvm.model.data.DailyForecastModel
import com.yahorb.mvvm.repository.data.getDailyForecasts
import com.yahorb.mvvm.repository.data.getLocation
import com.yahorb.mvvm.repository.local.WeatherRepository
import com.yahorb.mvvm.util.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class WeatherRepositoryImpl(
    private val weatherDatasource: WeatherDatasource,
    private val scheduler: SchedulerProvider
) : WeatherRepository {

    private val weatherCache = arrayListOf<DailyForecastModel>()

    override fun getSelectedWeatherDetail(
        id: String,
        onSuccess: (DailyForecastModel) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return Single.just(weatherCache.first { it.id == id })
            .with(scheduler)
            .subscribe(onSuccess, onError)
    }

    override fun searchWeather(
        location: String,
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable =
        weatherDatasource.geocode(location)
            .map { it.getLocation() ?: throw IllegalStateException("No Location data") }
            .flatMap { weatherDatasource.weather(it.lat, it.lng, "EN") }
            .map { it.getDailyForecasts() }
            .doOnSuccess { weatherCache.clear(); weatherCache.addAll(it) }
            .with(scheduler).subscribe(onSuccess, onError)

    override fun getWeather(
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return Single.just(weatherCache).with(scheduler).subscribe(onSuccess, onError)
    }
/*    override fun getWeather(
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    )): Disposable
    {
        //return Single.just(weatherCache).with(scheduler).subscribe(onSuccess, onError )
    }*/
}