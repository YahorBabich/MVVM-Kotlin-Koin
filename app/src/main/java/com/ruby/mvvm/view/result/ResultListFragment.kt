package com.ruby.mvvm.view.result

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_weather_list.*
import org.koin.android.architecture.ext.viewModel
import com.ruby.mvvm.R
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.model.data.ResultUIModel

class ResultListFragment : androidx.fragment.app.Fragment() {

    private lateinit var weatherResultAdapter: ResultListAdapter
    val viewModel: ResultViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiData.observe(this, androidx.lifecycle.Observer<ResultUIModel> {
            if (it != null) {
                val weatherList = it.list
                if (weatherList != weatherResultAdapter.list && weatherList.isNotEmpty()) {
                    displayWeather(weatherList)
                } else if (it.error != null) {
                    displayError(it.error)
                }
            }
        })

        weatherResultAdapter = ResultListAdapter(emptyList(), onItemClicked())
        weatherList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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

    fun displayError(error: Throwable?) {
        Snackbar.make(weatherList, "Got error : $error", Snackbar.LENGTH_LONG).show()
    }
}