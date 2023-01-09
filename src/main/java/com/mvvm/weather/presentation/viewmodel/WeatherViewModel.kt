package com.mvvm.weather.presentation.viewmodel

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mvvm.weather.domain.repository.WeatherRepository

class WeatherViewModel(private val repository: WeatherRepository) :
    ViewModel() {

    val userLocation = MutableLiveData<Location>()

    val weatherData = Transformations.switchMap(userLocation) {
        return@switchMap repository.getWeatherForecast(it.latitude, it.longitude)
    }
}