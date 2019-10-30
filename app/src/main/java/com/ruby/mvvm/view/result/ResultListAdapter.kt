package com.ruby.mvvm.view.result

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.joanzapata.iconify.widget.IconTextView
import com.ruby.mvvm.R
import com.ruby.mvvm.model.data.DailyForecastModel

class ResultListAdapter(
    var list: List<DailyForecastModel>,
    private val onClick: (DailyForecastModel) -> Unit
) : RecyclerView.Adapter<ResultListAdapter.WeatherResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherResultHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherResultHolder, position: Int) {
        holder.display(list[position], onClick)
    }

    override fun getItemCount() = list.size

    inner class WeatherResultHolder(item: View) :
        RecyclerView.ViewHolder(item) {
        private val weatherItemLayout = item.findViewById<LinearLayout>(R.id.weatherItemLayout)
        private val weatherItemForecast = item.findViewById<TextView>(R.id.weatherItemForecast)
        private val weatherItemTemp = item.findViewById<TextView>(R.id.weatherItemTemp)
        private val weatherItemIcon = item.findViewById<IconTextView>(R.id.weatherItemIcon)

        fun display(dailyForecastModel: DailyForecastModel, onClick: (DailyForecastModel) -> Unit) {
            weatherItemLayout.setOnClickListener { onClick(dailyForecastModel) }
            weatherItemForecast.text = dailyForecastModel.forecastString
            weatherItemIcon.text = dailyForecastModel.icon
            weatherItemTemp.text = dailyForecastModel.temperatureString
        }
    }
}