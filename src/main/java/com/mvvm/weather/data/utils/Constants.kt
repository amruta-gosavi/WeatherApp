package com.mvvm.weather.data.utils

import android.location.Location


object Constants {

    const val DATABASE_NAME = "weather-db"
    const val API_KEY = "ff9a4006119b24800a3183ad639f40b6"
    const val LOCATION_PERMISSION = 1000

    @JvmField
    val NEW_YORK_LOCATION = Location("default").apply {
        latitude = 40.6973
        longitude = -74.2195
    }

    @JvmField
    val LA_LOCATION = Location("default").apply {
        latitude = 34.0522
        longitude = -118.243683
    }

    @JvmField
    val CHICAGO_LOCATION = Location("default").apply {
        latitude = 41.8781
        longitude = -87.623177
    }

    @JvmField
    val HOUSTON_LOCATION = Location("default").apply {
        latitude = 29.7604
        longitude = -95.358421
    }

    @JvmField
    val SFO_LOCATION = Location("default").apply {
        latitude = 37.7749
        longitude = -122.431297
    }

    @JvmField
    val ATLANTA_LOCATION = Location("default").apply {
        latitude = 33.7488
        longitude = -84.386330
    }

    @JvmField
    val BOSTON_LOCATION = Location("default").apply {
        latitude = 42.3601
        longitude = -71.038887
    }

}
