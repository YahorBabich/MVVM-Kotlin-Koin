package com.ruby.mvvm.model.data

data class ResultModel(
    val list: List<DailyForecastModel> = emptyList(),
    val error: Throwable? = null
)