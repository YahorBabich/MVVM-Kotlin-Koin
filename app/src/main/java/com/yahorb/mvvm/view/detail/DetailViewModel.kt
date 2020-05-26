package com.yahorb.mvvm.view.detail

import androidx.lifecycle.MutableLiveData
import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.detail.model.DetailModel

class DetailViewModel(private val artistDao: ArtistDao) : BaseViewModel() {
    val uiData = MutableLiveData<DetailModel>()

    fun getDetail(id: Int) {
        launch {
            artistDao.getByID(id)
                .subscribe { artist ->
                    uiData.postValue(DetailModel(artist))
                }
        }
    }
}