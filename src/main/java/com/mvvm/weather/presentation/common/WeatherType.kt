package com.mvvm.weather.presentation.common

enum class WeatherType(var value: String) {
    Clear_day("clear-day"),
    Partly_cloudy_day("partly-cloudy-day"),
    Partly_cloudy_night("partly-cloudy-night"),
    Rain("rain"),
    Clear_night("clear-night");

    companion object {
        fun from(findValue: String): WeatherType =
            WeatherType.values().first { it.value == findValue }
    }

    fun stringValue(): String {
        when (this) {
            Clear_day -> {
                return "clear-day"
            }
            Partly_cloudy_day -> {
                return "partly-cloudy-day"
            }
            Partly_cloudy_night -> {
                return "partly-cloudy-night"
            }
            Rain -> {
                return "rain"
            }
            Clear_night ->{
                return "clear-night"
            }
            else -> return "partly-cloudy-day"
        }
    }
}
