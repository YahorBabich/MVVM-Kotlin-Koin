package com.yahorb.mvvm.view.list.model

import com.yahorb.mvvm.model.data.DailyForecastModel

data class ListModel(
    val list: List<DailyForecastModel> = emptyList(),
    val error: Throwable? = null
)