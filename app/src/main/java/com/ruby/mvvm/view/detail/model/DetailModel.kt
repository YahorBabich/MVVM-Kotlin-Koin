package com.ruby.mvvm.view.detail.model

import com.ruby.mvvm.model.data.DailyForecastModel

data class DetailModel(
    val model: DailyForecastModel? = null,
    val error: Throwable? = null
)