package com.yahorb.mvvm.view.search.model

data class SearchModel(
    val isSuccess: Boolean = false,
    val error: Throwable? = null
)