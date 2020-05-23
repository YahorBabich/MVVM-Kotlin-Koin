package com.yahorb.mvvm.view.detail

import android.os.Bundle
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.argument
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.model.data.DailyForecastModel
import com.yahorb.mvvm.view.Arguments.ARG_ADDRESS
import com.yahorb.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.yahorb.mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.detail.model.DetailModel
import kotlinx.android.synthetic.main.activity_weather_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailActivity : BaseActivity() {

    private val address by argument<String>(ARG_ADDRESS)
    private val now by argument<Date>(ARG_WEATHER_DATE)
    private val id by argument<String>(ARG_WEATHER_ITEM_ID)

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        viewModel.apply {
            observe(uiData, ::display)
        }

        viewModel.getDetail(id)
    }

    private fun display(model: DetailModel?) {
        model?.apply {
            if (this.model != null) {
                displayDetail(this.model)
            } else {
                displayError(error)
            }
        }
    }

    private fun displayDetail(weather: DailyForecastModel) {
        weatherTitle.text = getString(R.string.weather_title).format(address, now)
        weatherItemForecast.text = weather.forecastString
        weatherItemTemp.text = weather.temperature
    }
}