package com.mvvm.weather.data.persistence.dao

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mvvm.weather.data.persistence.local.WeatherDB
import com.mvvm.weather.presentation.model.DisplayableWeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object WeatherDBService {

    private val databaseJob = Job()
    val databaseScope = CoroutineScope(Dispatchers.IO + databaseJob)

    @JvmStatic
    fun insertResource(
        context: Context,
        list: MutableList<DisplayableWeatherData.DisplayableData>
    ) {
        databaseScope.launch {
            val databaseService = WeatherDB.getInstance(context)
            for (entry in list) {
                databaseService.weatherDao().insert(entry)
            }
        }
    }

    fun getWeatherForecastFromDB(
        context: Context,
        limit: Int,
        offset: Int
    ): MutableLiveData<List<DisplayableWeatherData.DisplayableData>> {
        val liveData = MutableLiveData<List<DisplayableWeatherData.DisplayableData>>()
        val databaseService = WeatherDB.getInstance(context)
        databaseScope.launch {
            val data = databaseService.weatherDao().queryWeatherData(limit, offset)
            liveData.postValue(data)
        }
        return liveData
    }

}