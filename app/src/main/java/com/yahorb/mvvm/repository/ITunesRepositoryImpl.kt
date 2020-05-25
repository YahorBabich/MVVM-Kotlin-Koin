package com.yahorb.mvvm.repository

import com.yahorb.mvvm.extension.with
import com.yahorb.mvvm.model.data.DailyForecastModel
import com.yahorb.mvvm.model.data.Term
import com.yahorb.mvvm.repository.local.ITuneRepository
import com.yahorb.mvvm.util.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class ITunesRepositoryImpl(
    private val iTunesAPI: ITunesAPI,
    private val scheduler: SchedulerProvider
) : ITuneRepository {

    private val weatherCache = arrayListOf<DailyForecastModel>()

    fun getSelectedWeatherDetail(
        id: String,
        onSuccess: (DailyForecastModel) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return Single.just(weatherCache.first { it.id == id })
            .with(scheduler)
            .subscribe(onSuccess, onError)
    }

/*    fun search(
        location: String,
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
    }
}
        /*iTunesAPI.geocode(location)
            .map { it.getLocation() ?: throw IllegalStateException("No Location data") }
            .flatMap { iTunesAPI.weather(it.lat, it.lng, "EN") }
            .map { it.getDailyForecasts() }
            .doOnSuccess { weatherCache.clear(); weatherCache.addAll(it) }
            .with(scheduler).subscribe(onSuccess, onError)*/

    fun getWeather(
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return Single.just(weatherCache).with(scheduler).subscribe(onSuccess, onError)
    }
    override fun getWeather(
        onSuccess: (List<DailyForecastModel>) -> Unit,
        onError: (Throwable) -> Unit
    )): Disposable
    {
        //return Single.just(weatherCache).with(scheduler).subscribe(onSuccess, onError )
    }
*/

    override fun search(
        term: String,
        onSuccess: (Term) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable {
        return iTunesAPI.search(term)
            .with(scheduler)
            .subscribe(onSuccess, onError)
    }
}