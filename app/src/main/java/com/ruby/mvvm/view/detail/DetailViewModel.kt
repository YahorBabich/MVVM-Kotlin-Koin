package com.ruby.mvvm.view.detail

import androidx.lifecycle.MutableLiveData
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.repository.WeatherRepository
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.util.ext.with
import com.ruby.mvvm.view.BaseViewModel

class DetailViewModel(
    val id: String,
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val uiData = MutableLiveData<DailyForecastModel>()

    fun getDetail(id: String) {
        launch {
            weatherRepository.getSelectedWeatherDetail(id).with(scheduler)
                .subscribe(
                    { d -> uiData.value = d },
                    { e -> println("got error : $e") })
        }
    }
}