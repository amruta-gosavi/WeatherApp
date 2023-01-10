package com.mvvm.weather.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.weather.presentation.model.DisplayableWeatherData

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherData ORDER BY time limit :limit offset :offset")
    fun queryWeatherData(limit: Int, offset: Int): List<DisplayableWeatherData.DisplayableData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: DisplayableWeatherData.DisplayableData)
}