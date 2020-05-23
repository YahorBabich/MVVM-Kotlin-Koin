package com.yahorb.mvvm.model.data

import java.util.*

data class DailyForecastModel(
    val forecastString: String,
    val icon: String,
    val temperatureLow: String,
    val temperatureHigh: String
) {
    val id = UUID.randomUUID().toString()
    val temperature = "$temperatureLow°C - $temperatureHigh°C"
}
