package com.yahorb.mvvm.view.detail.model

import com.yahorb.mvvm.model.data.DailyForecastModel

data class DetailModel(
    val model: DailyForecastModel? = null,
    val error: Throwable? = null
)