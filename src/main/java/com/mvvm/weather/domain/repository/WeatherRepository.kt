package com.mvvm.weather.domain.repository

import androidx.lifecycle.LiveData
import com.mvvm.weather.presentation.model.DisplayableWeatherData

/**
 *  This interface acts as a bridge between data and domain layer
 */
interface WeatherRepository {

    fun getWeatherForecast(
        lat: Double,
        lon: Double
    ): LiveData<DisplayableWeatherData.DisplayableDarkSky>
}