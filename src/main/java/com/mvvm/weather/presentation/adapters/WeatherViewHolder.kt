package com.mvvm.weather.presentation.adapters
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.weather.presentation.listeners.ActionListener
import com.mvvm.weather.databinding.ItemWeatherDataBinding
import com.mvvm.weather.presentation.model.DisplayableWeatherData

class WeatherViewHolder(
    private val binding: ItemWeatherDataBinding,
    private val callbackListener: ActionListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var weatherEntry: DisplayableWeatherData.DisplayableData
    private var pos: Int = -1

    init {
        binding.cardView.setOnClickListener(this)
    }

    fun bind(weatherEntry: DisplayableWeatherData.DisplayableData, pos: Int) {
        this.weatherEntry = weatherEntry
        this.pos = pos
        binding.weather = weatherEntry
        binding.executePendingBindings()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.cardView -> {
                callbackListener.onViewClicked(pos)
            }
        }
    }
}