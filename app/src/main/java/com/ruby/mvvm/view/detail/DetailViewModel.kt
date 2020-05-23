package com.ruby.mvvm.view.detail

import androidx.lifecycle.MutableLiveData
import com.ruby.mvvm.model.data.DetailModel
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.extension.with
import com.ruby.mvvm.view.BaseViewModel

class DetailViewModel(
    val id: String,
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val uiData = MutableLiveData<DetailModel>()

    fun getDetail(id: String) {
        launch {
            weatherRepository.getSelectedWeatherDetail(id).with(scheduler)
                .subscribe(
                    { d -> uiData.value = DetailModel(d) },
                    { e -> uiData.value = DetailModel(error = e) })
        }
    }
}