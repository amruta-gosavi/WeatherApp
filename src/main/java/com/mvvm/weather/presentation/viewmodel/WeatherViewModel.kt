package com.mvvm.weather.presentation.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mvvm.weather.domain.repository.WeatherRepository
import com.mvvm.weather.presentation.model.DisplayableWeatherData

class WeatherViewModel(private val repository: WeatherRepository) :
    ViewModel() {
    fun getWeatherData(userLocation: Location): LiveData<DisplayableWeatherData.DisplayableDarkSky> {
        return repository.getWeatherForecast(userLocation.latitude, userLocation.longitude)
    }
}