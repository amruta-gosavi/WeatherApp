package com.mvvm.weather.data.rest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


object DarkskyModel {

    data class Darksky(
        val latitude: Double,
        val longitude: Double,
        val timezone: String,
        val currently: Data,
        val daily: Daily,
        val minutely: Minutely,
        val hourly: Hourly
    ) : Serializable

    data class Daily(
        val summary: String?,
        val icon: String?,
        val data: MutableList<Data>
    ) : Serializable

    data class Minutely(
        val summary: String?,
        val icon: String?,
        val data: MutableList<Data>
    )

    data class Hourly(
        val summary: String?,
        val icon: String?,
        val data: MutableList<Data>
    )

    @Entity(tableName = "WeatherData")
    data class Data(

        @PrimaryKey
        @ColumnInfo(name = "time")
        val time: Long,

        @ColumnInfo(name = "summary")
        val summary: String? = "",

        @ColumnInfo(name = "icon")
        val icon: String? = "",

        @ColumnInfo(name = "nearestStormDistance")
        val nearestStormDistance: Int?,

        @ColumnInfo(name = "nearestStormBearing")
        val nearestStormBearing: Int?,

        @ColumnInfo(name = "precipIntensity")
        val precipIntensity: Double?,

        @ColumnInfo(name = "precipProbability")
        val precipProbability: Double?,

        @ColumnInfo(name = "temperature")
        val temperature: Double?,

        @ColumnInfo(name = "apparentTemperature")
        val apparentTemperature: Double?,

        @ColumnInfo(name = "apparentTemperatureHigh")
        val apparentTemperatureHigh: Double?,

        @ColumnInfo(name = "apparentTemperatureLow")
        val apparentTemperatureLow: Double?,

        @ColumnInfo(name = "apparentTemperatureHighTime")
        val apparentTemperatureHighTime: Long?,

        @ColumnInfo(name = "apparentTemperatureLowTime")
        val apparentTemperatureLowTime: Long?,

        @ColumnInfo(name = "dewPoint")
        val dewPoint: Double?,

        @ColumnInfo(name = "humidity")
        val humidity: Double?,

        @ColumnInfo(name = "pressure")
        val pressure: Double?,

        @ColumnInfo(name = "windSpeed")
        val windSpeed: Double?,

        @ColumnInfo(name = "windGust")
        val windGust: Double?,

        @ColumnInfo(name = "windGustTime")
        val windGustTime: Long?,

        @ColumnInfo(name = "windBearing")
        val windBearing: Int?,

        @ColumnInfo(name = "cloudCover")
        val cloudCover: Double?,

        @ColumnInfo(name = "uvIndex")
        val uvIndex: Int?,

        @ColumnInfo(name = "uvIndexTime")
        val uvIndexTime: Long?,

        @ColumnInfo(name = "visibility")
        val visibility: Double?,

        @ColumnInfo(name = "ozone")
        val ozone: Double?,

        @ColumnInfo(name = "sunriseTime")
        val sunriseTime: Long?,

        @ColumnInfo(name = "sunsetTime")
        val sunsetTime: Long?
    ) : Serializable
}

