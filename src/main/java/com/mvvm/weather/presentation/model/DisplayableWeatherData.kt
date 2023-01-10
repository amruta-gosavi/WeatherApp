package com.mvvm.weather.presentation.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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

    @Entity(tableName = "WeatherData")
    data class DisplayableData(
        @NonNull
        @PrimaryKey
        @ColumnInfo(name = "time")
        val time: String,
        @ColumnInfo(name = "summary")
        val summary: String? = "",
        @ColumnInfo(name = "icon")
        val icon: Int,
        @ColumnInfo(name = "temperature")
        val temperature: String?,
        @ColumnInfo(name = "dewPoint")
        val dewPoint: String?,
        @ColumnInfo(name = "humidity")
        val humidity: String?,
        @ColumnInfo(name = "windSpeed")
        val windSpeed: String?,
        @ColumnInfo(name = "cloudCover")
        val cloudCover: String?,
        @ColumnInfo(name = "uvIndex")
        val uvIndexTime: String?,
        @ColumnInfo(name = "visibility")
        val visibility: String?,
        @ColumnInfo(name = "ozone")
        val ozone: String?,
        @ColumnInfo(name = "precipProbability")
        val precipProbability: String?,
        @ColumnInfo(name = "day")
        val day: String?,
        @ColumnInfo(name = "windGust")
        val gusts: String?,
        @ColumnInfo(name = "sunriseTime")
        val sunrise: String?,
        @ColumnInfo(name = "sunsetTime")
        val sunset: String?,
        @ColumnInfo(name = "precipIntensity")
        val precipIntensity: String?,
        @ColumnInfo(name = "pressure")
        val pressure: String?,
        @ColumnInfo(name = "month")
        val month: String?
    ) : Serializable
}

