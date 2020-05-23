package com.ruby.mvvm.repository.data.weather

import com.google.gson.annotations.Expose
import java.util.*

class Simpleforecast {

    /**
     * @return The forecastday
     */
    /**
     * @param forecastday The forecastday
     */
    @Expose
    var forecastday: List<Forecastday_> = ArrayList()

}
