package com.ruby.mvvm.view.list

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruby.mvvm.R
import com.ruby.mvvm.extension.observe
import com.ruby.mvvm.model.data.DailyForecastModel
import com.ruby.mvvm.model.data.ResultModel
import com.ruby.mvvm.model.data.ResultSelectEvent
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
    //private val date: Date by argument(ARG_WEATHER_DATE)

    private val viewModel: ListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        viewModel.apply {
            observe(this.uiData, ::display)
            observe(this.selectEvent, ::next)
        }

        weatherResultAdapter = ListAdapter(emptyList(), onItemClicked())
        weatherList.layoutManager = LinearLayoutManager(this)
        weatherList.adapter = weatherResultAdapter
        viewModel.getWeatherList()
    }

    private fun display(model: ResultModel?) {
        model?.apply {
            if (error != null) {
                displayError(error)
            } else {
                displayWeather(list)
            }
        }
    }

    private fun next(result: ResultSelectEvent?) {
        result?.apply {
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
        weatherResultAdapter.update(weatherList)
    }

/*    companion object {
        private val EXTRA_FOO = "foo"

        fun start(caller: Context, bar: String){
            val intent = Intent(caller, ListActivity::class.java)
            intent.putExtra(EXTRA_FOO, bar)
            caller.startActivity(intent)
        }
    }*/

}