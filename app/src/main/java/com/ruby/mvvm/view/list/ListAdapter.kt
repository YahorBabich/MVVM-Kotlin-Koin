package com.ruby.mvvm.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruby.mvvm.R
import com.ruby.mvvm.model.data.DailyForecastModel
import kotlinx.android.synthetic.main.item_weather.view.*

class ListAdapter(
    private var list: List<DailyForecastModel>,
    private val onClick: (DailyForecastModel) -> Unit
) : RecyclerView.Adapter<ListAdapter.WeatherResultHolder>() {

    fun update(list: List<DailyForecastModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherResultHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherResultHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount() = list.size

    inner class WeatherResultHolder(private val item: View) :
        RecyclerView.ViewHolder(item) {

        fun bind(dailyForecastModel: DailyForecastModel, onClick: (DailyForecastModel) -> Unit) {
            item.apply {
                weatherItemLayout.setOnClickListener { onClick(dailyForecastModel) }
                weatherItemForecast.text = dailyForecastModel.forecastString
                weatherItemTemp.text = dailyForecastModel.temperature
            }
        }
    }
}