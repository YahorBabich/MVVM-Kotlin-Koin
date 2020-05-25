package com.yahorb.mvvm.view.list.model

import com.yahorb.mvvm.model.data.Artist

data class ListModel(
    val artists: List<Artist>? = emptyList(),
    val error: Throwable? = null
)