package com.yahorb.mvvm.view.detail.model

import com.yahorb.mvvm.model.data.Artist

data class DetailModel(
    val artist: Artist? = null,
    val error: Throwable? = null
)