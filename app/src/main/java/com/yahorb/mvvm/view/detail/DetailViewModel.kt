package com.yahorb.mvvm.view.detail

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.yahorb.mvvm.database.ArtistDao
import com.yahorb.mvvm.view.BaseViewModel
import com.yahorb.mvvm.view.detail.model.DetailModel

class DetailViewModel(private val artistDao: ArtistDao) : BaseViewModel() {
    val uiData = MutableLiveData<DetailModel>()

    @SuppressLint("CheckResult")
    fun getDetail(id: Int) {
        artistDao.getByID(id)
            .subscribe { artist ->
                uiData.postValue(DetailModel(artist))
            }
    }
}