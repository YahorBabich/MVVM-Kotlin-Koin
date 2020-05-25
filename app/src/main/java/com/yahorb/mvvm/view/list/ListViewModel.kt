package com.yahorb.mvvm.view.list

import android.annotation.SuppressLint
import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.SimpleLiveEvent
import com.yahorb.mvvm.view.list.model.ForwardModel
import com.yahorb.mvvm.view.list.model.ListModel

class ListViewModel(private val artistDao: ArtistDao) : BaseViewModel() {

    val selectEvent = SimpleLiveEvent<ForwardModel>()
    val uiData = SimpleLiveEvent<ListModel>()

    @SuppressLint("CheckResult")
    fun getITuneList() {
        artistDao.getAll
            .subscribe { artists ->
                uiData.postValue(ListModel(artists))
            }
    }

    fun details(id: String) {
        selectEvent.value = ForwardModel(id = id)
    }
}