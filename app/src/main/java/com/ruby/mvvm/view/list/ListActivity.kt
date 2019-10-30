package com.ruby.mvvm.view.list

import android.os.Bundle
import com.ruby.mvvm.R
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.util.ext.argument
import com.ruby.mvvm.view.Arguments.ARG_ADDRESS
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.ruby.mvvm.view.BaseActivity
import com.ruby.mvvm.view.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_weather.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import java.util.*

class ListActivity : BaseActivity() {

    private lateinit var weatherResultAdapter: ListAdapter
    val date: Date by argument(ARG_WEATHER_DATE)

    val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel.uiData.observe(this, androidx.lifecycle.Observer { model ->
            if (model != null) {
                val weatherList = model.list
                if (weatherList != weatherResultAdapter.list && weatherList.isNotEmpty()) {
                    displayWeather(weatherList)
                } else if (model.error != null) {
                    displayError(model.error)
                }
            }
        })

        viewModel.selectEvent.observe(this, androidx.lifecycle.Observer { event ->
            if (event != null) {
                startActivity<DetailActivity>(
                    ARG_ADDRESS to "address",
                    ARG_WEATHER_DATE to Date(),
                    ARG_WEATHER_ITEM_ID to event.id
                )
            }
        })

        weatherResultAdapter = ListAdapter(emptyList(), onItemClicked())
        weatherList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        weatherList.adapter = weatherResultAdapter

        viewModel.getWeatherList()
    }

    private fun onItemClicked(): (DailyForecastModel) -> Unit {
        return { weatherDetail ->
            viewModel.selectWeatherDetail(weatherDetail.id)
        }
    }

    fun displayWeather(weatherList: List<DailyForecastModel>) {
        weatherResultAdapter.list = weatherList
        weatherResultAdapter.notifyDataSetChanged()
    }
}