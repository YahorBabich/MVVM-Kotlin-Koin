package com.yahorb.mvvm.view.search

import com.yahorb.mvvm.repository.local.WeatherRepository
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.search.model.SearchModel

class SearchViewModel(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    val searchEvent = SimpleLiveEvent<SearchModel>()

    fun searchWeather(address: String) {
        launch {
            weatherRepository.searchWeather(address, onSuccess = { list ->
                searchEvent.postValue(SearchModel(isSuccess = true))
            }, onError = { err ->
                searchEvent.postValue(SearchModel(error = err))
            })
            /*.with(scheduler)
            .subscribe( { searchEvent.postValue(SearchModel(isSuccess = true)) },
                { err -> searchEvent.postValue(SearchModel(error = err)) })*/
        }
    }
}