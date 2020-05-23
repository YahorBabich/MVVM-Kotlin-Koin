package com.ruby.mvvm.view.search

import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.util.ext.with
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.view.BaseViewModel
import com.ruby.mvvm.view.SimpleLiveEvent
import com.ruby.mvvm.view.search.model.SearchModel

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