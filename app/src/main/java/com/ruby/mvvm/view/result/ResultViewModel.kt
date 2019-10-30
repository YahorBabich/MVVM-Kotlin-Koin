package com.ruby.mvvm.view.result

import androidx.lifecycle.MutableLiveData
import com.ruby.mvvm.model.data.ResultSelectEvent
import com.ruby.mvvm.model.data.ResultUIModel
import com.ruby.mvvm.repository.WeatherRepository
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.util.ext.with
import com.ruby.mvvm.view.BaseViewModel
import com.ruby.mvvm.view.SingleLiveEvent

class ResultViewModel(
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val selectEvent = SingleLiveEvent<ResultSelectEvent>()
    val uiData = MutableLiveData<ResultUIModel>()

    fun getWeatherList() {
        launch {
            weatherRepository.getWeather().with(scheduler)
                .subscribe({ list ->
                    uiData.value = ResultUIModel(list)
                }, { e ->
                    uiData.value = ResultUIModel(error = e)
                })
        }
    }

    fun selectWeatherDetail(id: String) {
        selectEvent.value = ResultSelectEvent(id = id)
    }
}