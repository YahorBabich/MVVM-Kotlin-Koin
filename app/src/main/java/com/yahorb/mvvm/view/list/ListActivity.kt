package com.yahorb.mvvm.view.list

import android.os.Bundle
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.argument
import com.yahorb.mvvm.extension.observe
import com.yahorb.mvvm.model.data.DailyForecastModel
import com.yahorb.mvvm.view.Arguments.ARG_ADDRESS
import com.yahorb.mvvm.view.Arguments.ARG_WEATHER_DATE
import com.yahorb.mvvm.view.Arguments.ARG_WEATHER_ITEM_ID
import com.yahorb.mvvm.view.BaseActivity
import com.yahorb.mvvm.view.detail.DetailActivity
import com.yahorb.mvvm.view.list.model.ForwardModel
import com.yahorb.mvvm.view.list.model.ListModel
import kotlinx.android.synthetic.main.activity_weather.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ListActivity : BaseActivity() {

    private lateinit var adapter: ListAdapter

    val date: Date by argument(ARG_WEATHER_DATE)

    private val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel.apply {
            observe(uiData, ::display)
            observe(selectEvent, ::next)
        }

        adapter = ListAdapter(emptyList(), onItemClicked())
        weatherList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        weatherList.adapter = adapter

        viewModel.getITuneList()
    }

    private fun display(model: ListModel?) {
        model?.apply {
            if (list != adapter.list && list.isNotEmpty()) {
                displayWeather(list)
            } else if (error != null) {
                onError(error)
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
        adapter.list = weatherList
        adapter.notifyDataSetChanged()
    }
}