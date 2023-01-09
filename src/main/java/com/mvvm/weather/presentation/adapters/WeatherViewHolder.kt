package com.mvvm.weather.presentation.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weather.presentation.common.WeatherType
import com.mvvm.weather.presentation.listeners.ActionListener
import com.mvvm.weather.R
import com.mvvm.weather.databinding.ItemWeatherDataBinding
import com.mvvm.weather.presentation.model.DisplayableWeatherData

class WeatherViewHolder(
    private val binding: ItemWeatherDataBinding,
    private val callbackListener: ActionListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var weatherEntry: DisplayableWeatherData.DisplayableData
    private lateinit var context: Context

    init {
        binding.cardView.setOnClickListener(this)
    }

    fun bind(weatherEntry: DisplayableWeatherData.DisplayableData, context: Context) {
        this.weatherEntry = weatherEntry
        this.context = context

        binding.weather = weatherEntry

        when (weatherEntry.icon) {
            WeatherType.Clear_day.stringValue() -> {
                binding.image = R.drawable.ic_weather_clear_day
            }
            WeatherType.Partly_cloudy_day.stringValue() -> {
              binding.image = R.drawable.ic_weather_partly_cloudy_day
            }
            WeatherType.Partly_cloudy_night.stringValue() -> {
                binding.image = R.drawable.ic_weather_party_cloudy_night
            }
            WeatherType.Rain.stringValue() -> {
               binding.image = R.drawable.ic_weather_rain
            }
        }

        binding.executePendingBindings()
    }

    override fun onClick(v: View?) {
        when (v) {
        }
    }
}