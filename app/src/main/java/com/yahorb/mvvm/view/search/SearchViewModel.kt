package com.yahorb.mvvm.view.search

import com.yahorb.mvvm.extension.with
import com.yahorb.mvvm.repository.local.WeatherRepository
import com.yahorb.mvvm.util.rx.SchedulerProvider
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.search.model.SearchModel

class SearchViewModel(
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val searchEvent = SimpleLiveEvent<SearchModel>()

    fun searchWeather(address: String) {
        launch {
            weatherRepository.searchWeather(address)
                .with(scheduler)
                .subscribe(
                    {
                        searchEvent.postValue(
                            SearchModel(
                                isSuccess = true
                            )
                        )
                    },
                    { err ->
                        searchEvent.postValue(
                            SearchModel(
                                error = err
                            )
                        )
                    })
        }
    }
}