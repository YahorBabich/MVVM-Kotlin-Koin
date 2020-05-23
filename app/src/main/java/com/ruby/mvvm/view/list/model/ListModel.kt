package com.ruby.mvvm.view.list.model

import com.ruby.mvvm.model.data.DailyForecastModel

data class ListModel(
    val list: List<DailyForecastModel> = emptyList(),
    val error: Throwable? = null
)