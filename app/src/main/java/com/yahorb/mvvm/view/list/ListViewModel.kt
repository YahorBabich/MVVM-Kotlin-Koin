package com.yahorb.mvvm.view.list

import androidx.lifecycle.MutableLiveData
import com.yahorb.mvvm.repository.local.ITuneRepository
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.list.model.ForwardModel
import com.yahorb.mvvm.view.list.model.ListModel

class ListViewModel(
    private val weatherRepository: ITuneRepository
) : BaseViewModel() {

    val selectEvent = SimpleLiveEvent<ForwardModel>()
    val uiData = MutableLiveData<ListModel>()

    fun getITuneList() {
/*        launch {
            weatherRepository.getWeather(onSuccess = { list ->
                uiData.value = ListModel(list)
            }, onError = { e ->
                uiData.value = ListModel(error = e)
            })
            *//*weatherRepository.getWeather()
                .with(scheduler)
                .subscribe({ list ->
                    uiData.value = ListModel(list)
                }, { e ->
                    uiData.value = ListModel(error = e)
                })*//*
        }*/
    }

    fun selectWeatherDetail(id: String) {
        selectEvent.value = ForwardModel(id = id)
    }
}