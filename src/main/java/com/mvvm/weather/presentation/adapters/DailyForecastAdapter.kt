package com.mvvm.weather.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weather.presentation.listeners.ActionListener
import com.mvvm.weather.databinding.ItemWeatherDataBinding
import com.mvvm.weather.presentation.model.DisplayableWeatherData

class WeatherAdapter(
    private val action: ActionListener
) : RecyclerView.Adapter<WeatherViewHolder>() {

    var records = emptyList<DisplayableWeatherData.DisplayableData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var binding = ItemWeatherDataBinding.inflate(layoutInflater, parent, false);
        return WeatherViewHolder(binding, action)
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(records[position], position)
    }
}