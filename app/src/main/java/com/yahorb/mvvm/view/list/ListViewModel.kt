package com.yahorb.mvvm.view.list

import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.list.model.ListModel

class ListViewModel(private val artistDao: ArtistDao) : BaseViewModel() {
    val uiData = SimpleLiveEvent<ListModel>()

    fun getITuneList() {
        launch {
            artistDao.getAll
                .subscribe { artists ->
                    uiData.postValue(ListModel(artists))
                }
        }
    }
}