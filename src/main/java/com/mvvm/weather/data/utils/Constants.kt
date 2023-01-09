package com.mvvm.weather.data.utils

import android.location.Location


object Constants {

    const val DATABASE_NAME = "weather-db"
    const val API_KEY = "ff9a4006119b24800a3183ad639f40b6"
    const val DEFAULT_LOCATION_NAME = "New York City"

    @JvmField
    val NEW_YORK_LOCATION = Location("default").apply {
        latitude = 40.6973
        longitude = -74.2195
    }

    const val LOCATION_PERMISSION = 1000

}
