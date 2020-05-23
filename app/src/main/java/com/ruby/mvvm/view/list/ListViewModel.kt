package com.ruby.mvvm.view.list

import androidx.lifecycle.MutableLiveData
import com.ruby.mvvm.model.data.ResultSelectEvent
import com.ruby.mvvm.model.data.ResultModel
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.extension.with
import com.ruby.mvvm.view.BaseViewModel
import com.ruby.mvvm.view.SimpleLiveEvent

class ListViewModel(
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val selectEvent = SimpleLiveEvent<ResultSelectEvent>()
    val uiData = MutableLiveData<ResultModel>()

    fun getWeatherList() {
        launch {
            weatherRepository.getWeather()
                .with(scheduler)
                .subscribe({ list ->
                    uiData.value = ResultModel(list)
                }, { e ->
                    uiData.value = ResultModel(error = e)
                })
        }
    }

    fun selectWeatherDetail(id: String) {
        selectEvent.value = ResultSelectEvent(id = id)
    }
}