package com.mvvm.weather.presentation.common

enum class WeatherUnits(var value: String) {
    MPH("MPH"),
    M_PER_SECOND("m/s"),
    PERCENT("%"),
    MODERATE("Moderate"),
    LOW("Low"),
    HIGH("High"),
    NO_UNIT("UNIT");

    companion object {
        fun from(findValue: String): WeatherUnits =
            WeatherUnits.values().first { it.value == findValue }
    }

    fun stringValue(): String {
        when (this) {
            MPH -> {
                return "clear-day"
            }
            M_PER_SECOND -> {
                return "partly-cloudy-day"
            }
            PERCENT -> {
                return "partly-cloudy-night"
            }
            MODERATE -> {
                return "rain"
            }
            LOW -> {
                return "Low"
            }
            HIGH -> {
                return "High"
            }
            else -> return ""
        }
    }
}
