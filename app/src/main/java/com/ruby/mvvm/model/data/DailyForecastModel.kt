package com.ruby.mvvm.model.data

import java.util.*

data class DailyForecastModel(
    val forecastString: String,
    val icon: String,
    val temperatureLow: String,
    val temperatureHigh: String
) {
    val id = UUID.randomUUID().toString()
    val temperatureString = "$temperatureLow°C - $temperatureHigh°C"
}
