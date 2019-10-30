package com.ruby.mvvm.model.data

data class DetailModel(
    val model: DailyForecastModel? = null,
    val error: Throwable? = null
)