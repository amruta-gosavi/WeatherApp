package com.mvvm.weather.data.utils

import android.content.Context
import com.mvvm.weather.data.repositoryImpl.WeatherRepoImpl
import com.mvvm.weather.domain.repository.WeatherRepository
import com.mvvm.weather.presentation.viewmodel.WeatherViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getConnectionUtils(context: Context): ConnectionUtils {

        return ConnectionUtils(context)
    }

    private fun getWeatherRepository(context: Context): WeatherRepository {
        return WeatherRepoImpl.getInstance(
            context,
            getConnectionUtils(context)
        )
    }

    fun provideWeatherViewModelFactory(
        context: Context
    ): WeatherViewModelFactory {
        val repository = getWeatherRepository(context)
        return WeatherViewModelFactory(repository)
    }
}
