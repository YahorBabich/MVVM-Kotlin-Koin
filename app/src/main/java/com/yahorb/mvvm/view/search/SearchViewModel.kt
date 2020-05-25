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
) : BaseViewModel() {

    val searchEvent = SimpleLiveEvent<SearchModel>()

    fun search(term: String) {
        var search = term
        if (term.isEmpty()) {
            search = "Jackson"
        }
        launch {
            repository.search(search, onSuccess = { term ->
                launch {
                    artistDao.insertAll(*term.results.toTypedArray()).with(scheduler).subscribe({
                        Log.d(SearchViewModel::javaClass.name, "db was filled")
                        searchEvent.value = SearchModel(true)
                    }, {
                        searchEvent.value = SearchModel(error = it)
                    })
                }
            }, onError = {
                searchEvent.value = SearchModel(error = it)
            })
        }
    }

    fun deleteAll() {
        launch {
            artistDao.deleteAll().with(scheduler).subscribe({
                Log.d(SearchViewModel::javaClass.name, "db was deleted")
            }, {
                searchEvent.value = SearchModel(error = it)
            })
        }
    }
}