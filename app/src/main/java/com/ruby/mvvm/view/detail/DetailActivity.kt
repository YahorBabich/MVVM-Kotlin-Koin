package com.ruby.mvvm.view.detail

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_weather_detail.*
import com.ruby.mvvm.R
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.extension.argument
import com.ruby.mvvm.view.Arguments.ARG_ADDRESS
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.ruby.mvvm.view.BaseActivity
import org.koin.android.architecture.ext.viewModel
import java.util.*

class DetailActivity : BaseActivity() {

    private val address by argument<String>(ARG_ADDRESS)
    private val now by argument<Date>(ARG_WEATHER_DATE)
    private val id by argument<String>(ARG_WEATHER_ITEM_ID)

    val detailViewModel: DetailViewModel by viewModel { mapOf("id" to id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        detailViewModel.uiData.observe(this, androidx.lifecycle.Observer { detail ->
            if (detail?.model != null) {
                displayDetail(detail.model)
            } else {
                displayError(detail.error)
            }
        })
        detailViewModel.getDetail(id)
    }

    fun displayDetail(weather: DailyForecastModel) {
        weatherTitle.text = getString(R.string.weather_title).format(address, now)
        weatherItemForecast.text = weather.forecastString
        weatherItemTemp.text = weather.temperature
    }
}