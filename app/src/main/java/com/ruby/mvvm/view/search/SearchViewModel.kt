package com.ruby.mvvm.view.search

import com.ruby.mvvm.model.data.SearchEvent
import com.ruby.mvvm.repository.local.WeatherRepository
import com.ruby.mvvm.extension.with
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.view.BaseViewModel
import com.ruby.mvvm.view.SimpleLiveEvent

class SearchViewModel(
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val searchEvent = SimpleLiveEvent<SearchEvent>()

    fun searchWeather(address: String) {
        launch {
            weatherRepository.searchWeather(address)
                .with(scheduler)
                .subscribe(
                    {
                        searchEvent.postValue(SearchEvent(isSuccess = true))
                    },
                    { err ->
                        searchEvent.postValue(SearchEvent(error = err))
                    })
        }
    }
}