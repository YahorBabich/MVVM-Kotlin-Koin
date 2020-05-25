package com.yahorb.mvvm.repository.local

import com.yahorb.mvvm.model.data.DailyForecastModel
import com.yahorb.mvvm.model.data.Term
import io.reactivex.disposables.Disposable

interface ITuneRepository {
    /*fun search(
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
    ): Disposable*/

    fun search(term: String,
               onSuccess: (Term) -> Unit,
               onError: (Throwable) -> Unit): Disposable
}