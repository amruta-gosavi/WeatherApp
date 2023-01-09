package com.mvvm.weather.data.repositoryImpl

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.mvvm.weather.data.RestApi
import com.mvvm.weather.data.datamapper.datamapper.WeatherDataMapper
import com.mvvm.weather.data.persistence.dao.WeatherDBService
import com.mvvm.weather.data.utils.ConnectionUtils
import com.mvvm.weather.data.utils.Constants
import com.mvvm.weather.domain.repository.WeatherRepository
import com.mvvm.weather.presentation.model.DisplayableWeatherData
import kotlinx.coroutines.*

/**
 * Implementation of WeatherRepository: The data is emitted either from api or from db(sqllite)
 */
class WeatherRepoImpl(
    val context: Context,
    val utils: ConnectionUtils
) : WeatherRepository {


    override fun getWeatherForecast(
        lat: Double,
        lon: Double
    ): MutableLiveData<DisplayableWeatherData.DisplayableDarkSky> {
        val hasConnection = utils.isConnectedToInternet()
//        return if (hasConnection) {
//            getWeatherForecastFromAPI(lat, lon)
//        } else {
//           // WeatherDBService.getWeatherForecastFromDB(context, 7, 0)
//        }
        return getWeatherForecastFromAPI(lat, lon)
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance(context: Context, utils: ConnectionUtils) =
            instance ?: synchronized(this) {
                instance ?: WeatherRepoImpl(context, utils).also { instance = it }
            }
    }


    private fun getWeatherForecastFromAPI(
        lat: Double,
        lon: Double
    ): MutableLiveData<DisplayableWeatherData.DisplayableDarkSky> {
        val liveData = MutableLiveData<DisplayableWeatherData.DisplayableDarkSky>()

        CoroutineScope(Dispatchers.IO).launch {
            val response = RestApi.instance?.weatherApi?.getForecast(
                RestApi.instance?.getHeaders()!!, Constants.API_KEY, lat.toString(), lon.toString()
            )
            withContext(Dispatchers.Main) {
                if (response?.isSuccessful == true) {
                    val dailyData = response.body()?.let { WeatherDataMapper().transform(it) }
                    if (dailyData != null) {
                        liveData.value = dailyData
                        response?.body()?.daily?.data?.let {
                            WeatherDBService.insertResource(
                                context,
                                it
                            )
                        }
                    }
                } else {
                    //handle error/retry logic
                    liveData.value = null
                }
            }

        }
        return liveData
    }
}
