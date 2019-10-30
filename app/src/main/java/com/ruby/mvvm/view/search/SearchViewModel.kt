package com.ruby.mvvm.view.search

import androidx.lifecycle.MutableLiveData
import com.ruby.mvvm.model.data.SearchEvent
import com.ruby.mvvm.model.data.SearchUIModel
import com.ruby.mvvm.repository.WeatherRepository
import com.ruby.mvvm.util.rx.SchedulerProvider
import com.ruby.mvvm.util.ext.with
import com.ruby.mvvm.view.BaseViewModel
import com.ruby.mvvm.view.SingleLiveEvent

class SearchViewModel(
    private val weatherRepository: WeatherRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    val searchEvent = SingleLiveEvent<SearchEvent>()
    val uiData = MutableLiveData<SearchUIModel>()

    fun searchWeather(address: String) {
        launch {
            uiData.value = SearchUIModel(address)

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