package com.ruby.mvvm.model.data

data class SearchEvent(
    val isSuccess: Boolean = false,
    val error: Throwable? = null
)