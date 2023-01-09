package com.mvvm.weather.presentation.model

object DisplayableWeatherData {

    data class DisplayableDarkSky(
        val currently: DisplayableData,
        val daily: DisplayableDaily
    )

    data class DisplayableDaily(
        val summary: String?,
        val icon: String?,
        val data: MutableList<DisplayableData>?
    )

    data class DisplayableData(
        val time: String?,
        val summary: String? = "",
        val icon: Int,
        val temperature: String?,
        val dewPoint: String?,
        val humidity: String?,
        val windSpeed: String?,
        val cloudCover: String?,
        val uvIndexTime: String?,
        val visibility: String?,
        val ozone: String?,
        val precipProbability: String?,
        val day: String?,
        val gusts: String?,
        val sunrise: String?,
        val sunset: String?,
        val precipIntensity: String?,
        val pressure: String?,
        val month: String?
    )
}

