package com.mvvm.weather.data.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mvvm.weather.data.rest.model.DarkskyModel

@Dao
interface WeatherDao {

//    @Query("SELECT * FROM weatherdata")
//    suspend fun queryWeatherData(): List<DarkskyModel.Data>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(data: DarkskyModel.Data)
}