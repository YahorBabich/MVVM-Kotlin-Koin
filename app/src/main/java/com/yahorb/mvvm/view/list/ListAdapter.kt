package com.yahorb.mvvm.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yahorb.mvvm.R
import com.yahorb.mvvm.model.data.DailyForecastModel

class ListAdapter(
    var list: List<DailyForecastModel>,
    private val onClick: (DailyForecastModel) -> Unit
) : RecyclerView.Adapter<ListAdapter.WeatherResultHolder>() {

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
        private val weatherItemLayout = item.findViewById<ConstraintLayout>(R.id.weatherItemLayout)
        private val weatherItemForecast = item.findViewById<TextView>(R.id.weatherItemForecast)
        private val weatherItemTemp = item.findViewById<TextView>(R.id.weatherItemTemp)

        fun display(dailyForecastModel: DailyForecastModel, onClick: (DailyForecastModel) -> Unit) {
            weatherItemLayout.setOnClickListener { onClick(dailyForecastModel) }
            weatherItemForecast.text = dailyForecastModel.forecastString
            weatherItemTemp.text = dailyForecastModel.temperature
        }
    }
}