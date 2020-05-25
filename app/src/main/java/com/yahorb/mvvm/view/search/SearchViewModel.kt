package com.yahorb.mvvm.view.search

import android.util.Log
import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.extension.with
import com.yahorb.mvvm.repository.local.ITuneRepository
import com.yahorb.mvvm.util.rx.SchedulerProvider
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.search.model.SearchModel

class SearchViewModel(
    private val repository: ITuneRepository,
    private val scheduler: SchedulerProvider,
    private val artistDao: ArtistDao
) :
    BaseViewModel() {

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
            repository.search(search, onSuccess = { term ->
                artistDao.insertAll(*term.results.toTypedArray()).with(scheduler).subscribe({
                    Log.d(SearchViewModel::javaClass.name, "db was filled")
                    searchEvent.value = SearchModel(true)
                }, {
                    searchEvent.value = SearchModel(error = it)
                })
            }, onError = {
                searchEvent.value = SearchModel(error = it)
            })
        }
    }
}