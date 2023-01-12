package com.mvvm.weather.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.room.Room
import com.mvvm.weather.data.persistence.dao.WeatherDao
import com.mvvm.weather.data.persistence.local.WeatherDB
import org.junit.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.mvvm.weather.presentation.model.DisplayableWeatherData

class WeatherDaoTest {
    private lateinit var database: WeatherDB
    private lateinit var weatherDao: WeatherDao
    private lateinit var dataFromDb:List<DisplayableWeatherData.DisplayableData>


    private val day1 = DisplayableWeatherData.DisplayableData(
        100993383L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")
    private val day2 = DisplayableWeatherData.DisplayableData(
        100983383L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")
    private val day3 = DisplayableWeatherData.DisplayableData(
        100983384L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")
    private val day4 = DisplayableWeatherData.DisplayableData(
        130983383L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")
    private val day5 = DisplayableWeatherData.DisplayableData(
        102983383L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")
    private val day6 = DisplayableWeatherData.DisplayableData(
        100984383L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")

    private val day7 = DisplayableWeatherData.DisplayableData(
        100083383L.toString(), "Clear Sunny day", 1, ""+22, ""+12,
        ""+23.00, ""+86.00, ""+87.00, ""+90.00, ""+89.00, ""+90.00,
        ""+100993383L, ""+100993383L, ""+12.00, ""+45.00, ""+103.00, ""+34.00,
        ""+45.00, "January")



    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, WeatherDB::class.java).build()
        weatherDao = database.weatherDao()
        //insert dummy data
        database.weatherDao().insert(day1)
        database.weatherDao().insert(day2)
        database.weatherDao().insert(day3)
        database.weatherDao().insert(day4)
        database.weatherDao().insert(day5)
        database.weatherDao().insert(day6)
        database.weatherDao().insert(day7)
    }

    @After
    fun closeDb() {
        database.close()
    }

    //Query database and check if we get weatherList count :>7
    @Test
    fun testWeatherCount() {
        val weatherList = weatherDao.queryWeatherData(7, 0)
        getValue(weatherList)
    }

    fun getValue(list: List<DisplayableWeatherData.DisplayableData>){
        dataFromDb = list
        assert(dataFromDb.size == 7)
    }

    @SuppressLint("StringFormatInvalid")
    fun handleError(e:Throwable){
        Log.e("onError", e.printStackTrace().toString())
    }
}