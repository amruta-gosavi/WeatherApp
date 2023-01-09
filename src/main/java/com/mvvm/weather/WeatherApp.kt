package com.mvvm.weather


import android.app.Application
import com.mvvm.weather.data.persistence.local.WeatherDB

class WeatherApp : Application() {

    companion object {
        var weatherDB: WeatherDB? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}
