package com.mvvm.weather.data.persistence.dao

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mvvm.weather.data.datamapper.datamapper.WeatherDataMapper
import com.mvvm.weather.data.persistence.local.WeatherDB
import com.mvvm.weather.data.rest.model.DarkskyModel
import com.mvvm.weather.presentation.model.DisplayableWeatherData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object WeatherDBService {

    private val databaseJob = Job()
    val databaseScope = CoroutineScope(Dispatchers.IO + databaseJob)
    private val weatherDataMapper = WeatherDataMapper()

    @JvmStatic
    fun insertResource(context: Context, list: List<DarkskyModel.Data>) {
        databaseScope.launch {
            val databaseService = WeatherDB.getInstance(context)
            for (entry in list) {
              //  databaseService?.weatherDao()?.insert(entry)
            }
        }
    }

    fun getWeatherForecastFromDB(
        context: Context,
        limit: Int,
        offset: Int
    ): MutableLiveData<DisplayableWeatherData.DisplayableDarkSky> {
        val liveData = MutableLiveData<DisplayableWeatherData.DisplayableDarkSky>()
        val databaseService = WeatherDB.getInstance(context)
        databaseScope.launch {
          //  val data = databaseService.weatherDao().queryWeatherData(limit, offset)
           // liveData.value = weatherDataMapper.transformFromDBData(data)
        }
        return liveData
    }

}