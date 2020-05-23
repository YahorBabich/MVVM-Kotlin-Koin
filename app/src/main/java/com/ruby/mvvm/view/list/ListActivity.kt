package com.ruby.mvvm.view.list

import android.os.Bundle
import com.ruby.mvvm.R
import com.ruby.mvvm.extension.argument
import com.ruby.mvvm.extension.observe
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.view.Arguments.ARG_ADDRESS
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.ruby.mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.ruby.mvvm.view.BaseActivity
import com.ruby.mvvm.view.detail.DetailActivity
import com.ruby.mvvm.view.list.model.ForwardModel
import com.ruby.mvvm.view.list.model.ListModel
import kotlinx.android.synthetic.main.activity_weather.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import java.util.*

class ListActivity : BaseActivity() {

    private lateinit var weatherResultAdapter: ListAdapter
    val date: Date by argument(ARG_WEATHER_DATE)

    private val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel.apply {
            observe(uiData, ::display)
            observe(selectEvent, ::next)
        }

        weatherResultAdapter = ListAdapter(emptyList(), onItemClicked())
        weatherList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        weatherList.adapter = weatherResultAdapter

        viewModel.getWeatherList()
    }

    private fun display(model: ListModel?) {
        model?.apply {
            val weatherList = list
            if (weatherList != weatherResultAdapter.list && weatherList.isNotEmpty()) {
                displayWeather(weatherList)
            } else if (error != null) {
                displayError(error)
            }
        }
    }

    private fun next(model: ForwardModel?) {
        model?.apply {
            startActivity<DetailActivity>(
                ARG_ADDRESS to "address",
                ARG_WEATHER_DATE to Date(),
                ARG_WEATHER_ITEM_ID to id
            )
        }
    }

    private fun onItemClicked(): (DailyForecastModel) -> Unit {
        return { weatherDetail ->
            viewModel.selectWeatherDetail(weatherDetail.id)
        }
    }

    private fun displayWeather(weatherList: List<DailyForecastModel>) {
        weatherResultAdapter.list = weatherList
        weatherResultAdapter.notifyDataSetChanged()
    }
}