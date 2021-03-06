package com.yahorb.mvvm.view.detail

import androidx.lifecycle.MutableLiveData
import com.yahorb.mvvm.repository.local.WeatherRepository
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.detail.model.DetailModel

class DetailViewModel(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    val uiData = MutableLiveData<DetailModel>()

    fun getDetail(id: String) {
        launch {
            /*weatherRepository.getSelectedWeatherDetail(id).with(scheduler)
                .subscribe(
                    { d -> uiData.value = DetailModel(d) },
                    { e -> uiData.value = DetailModel(error = e) })*/

            weatherRepository.getSelectedWeatherDetail(id, onSuccess = { d ->
                uiData.value = DetailModel(d)
            }, onError = { e ->
                uiData.value = DetailModel(error = e)
            })
        }
    }
}