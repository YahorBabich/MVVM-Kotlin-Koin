package com.ruby.mvvm.view.list

import androidx.lifecycle.MutableLiveData
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.util.ext.with
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.view.BaseViewModel
import com.ruby.mvvm.view.SimpleLiveEvent
import com.ruby.mvvm.view.list.model.ForwardModel
import com.ruby.mvvm.view.list.model.ListModel

class ListViewModel(
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val selectEvent = SimpleLiveEvent<ForwardModel>()
    val uiData = MutableLiveData<ListModel>()

    fun getWeatherList() {
        launch {
            weatherRepository.getWeather()
                .with(scheduler)
                .subscribe({ list ->
                    uiData.value = ListModel(list)
                }, { e ->
                    uiData.value = ListModel(error = e)
                })
        }
    }

    fun selectWeatherDetail(id: String) {
        selectEvent.value = ForwardModel(id = id)
    }
}