package com.yahorb.mvvm.view.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yahorb.mvvm.R
import com.yahorb.mvvm.extension.inflater
import com.yahorb.mvvm.model.data.DailyForecastModel
import kotlinx.android.synthetic.main.item_weather.view.*

class ListAdapter(
    var list: List<DailyForecastModel>,
    private val onClick: (DailyForecastModel) -> Unit
) : RecyclerView.Adapter<ListAdapter.WeatherResultHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherResultHolder {
        val view = parent.inflater(R.layout.item_weather)
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherResultHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherResultHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class WeatherResultHolder(private val item: View) :
        RecyclerView.ViewHolder(item) {

        fun bind(dailyForecastModel: DailyForecastModel) {
            item.weatherItemLayout.setOnClickListener { onClick(dailyForecastModel) }
            item.weatherItemForecast.text = dailyForecastModel.forecastString
            item.weatherItemTemp.text = dailyForecastModel.temperature
        }
    }
}