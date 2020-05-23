package com.ruby.mvvm.view.search.model

data class SearchModel(
    val isSuccess: Boolean = false,
    val error: Throwable? = null
)