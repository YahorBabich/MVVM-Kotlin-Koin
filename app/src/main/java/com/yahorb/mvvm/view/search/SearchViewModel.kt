package com.yahorb.mvvm.view.search

import com.yahorb.mvvm.repository.local.ITuneRepository
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.search.model.SearchModel

class SearchViewModel(private val repository: ITuneRepository) : BaseViewModel() {

    val searchEvent = SimpleLiveEvent<SearchModel>()

/*    fun searchWeather(address: String) {
        launch {
            repository.search(address, onSuccess = { list ->
                searchEvent.postValue(SearchModel(isSuccess = true))
            }, onError = { err ->
                searchEvent.postValue(SearchModel(error = err))
            })
            *//*.with(scheduler)
            .subscribe( { searchEvent.postValue(SearchModel(isSuccess = true)) },
                { err -> searchEvent.postValue(SearchModel(error = err)) })*//*
        }
    }*/

    fun search(term: String) {
        var search = term
        if (term.isEmpty()) {
            search = "Jackson"
        }
        launch {
            repository.search(search, onSuccess = {
                val result = it.results
                searchEvent.value = SearchModel(true)
            }, onError = {
                searchEvent.value = SearchModel(error = it)
            })
        }
    }
}