package com.ruby.mvvm.model.data

data class ResultUIModel(
    val list: List<DailyForecastModel> = emptyList(),
    val error: Throwable? = null
)