package com.mvvm.weather.data.persistence.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.weather.data.persistence.dao.WeatherDao
import com.mvvm.weather.data.rest.model.DarkskyModel
import com.mvvm.weather.data.utils.Constants
import com.mvvm.weather.presentation.model.DisplayableWeatherData

@Database(
    entities = [DisplayableWeatherData.DisplayableData::class],
    version = 2,
    exportSchema = false
)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: WeatherDB? = null

        fun getInstance(context: Context): WeatherDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDB {
            return Room.databaseBuilder(context, WeatherDB::class.java, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}