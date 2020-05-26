package com.yahorb.mvvm.view.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.extension.with
import com.yahorb.mvvm.model.data.Artist
import com.yahorb.mvvm.model.data.Term
import com.yahorb.mvvm.repository.ITunesRepository
import com.yahorb.mvvm.util.rx.SchedulerProvider
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.search.model.SearchModel

class SearchViewModel(
    private val repository: ITunesRepository,
    private val scheduler: SchedulerProvider,
    private val artistDao: ArtistDao
) : BaseViewModel() {

    val searchEvent = MutableLiveData<SearchModel>()

    fun search(term: String) {
        launch {
            repository.search(term, onSuccess = { term ->
                onSearchSuccess(term)
            }, onError = {
                searchEvent.value = SearchModel(error = it)
            })
        }
    }

    fun onSearchSuccess(term: Term) {
        if (term.resultCount != 0 && term.results.isNotEmpty()) {
            insertAll(term.results)
        } else {
            searchEvent.value = SearchModel(true)
        }
    }

    private fun insertAll(list: List<Artist>) {
        launch {
            artistDao.insertAll(*list.toTypedArray()).with(scheduler)
                .subscribe({
                    Log.d(SearchViewModel::javaClass.name, "db was filled")
                    searchEvent.value = SearchModel(true)
                }, {
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