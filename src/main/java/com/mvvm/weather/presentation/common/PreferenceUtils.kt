package com.mvvm.weather.presentation.common

import android.content.Context
import android.content.SharedPreferences
import com.mvvm.weather.R

object PreferenceUtils {

    private const val city_key:String = "city"

    fun savePreferences(context: Context, city:String){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("WeatherPref",
            Context.MODE_PRIVATE
        )
        val myEdit = sharedPreferences.edit()
        myEdit.putString(city_key, city)
        myEdit.commit()
    }

    fun getPreferences(context: Context):String{
        val sharedPreferences = context.getSharedPreferences("WeatherPref", Context.MODE_PRIVATE)
        var value:String? = null
        if(sharedPreferences.contains(city_key)){
           value =  sharedPreferences.getString(city_key, context.getString(R.string.new_york))
        }
        return value ?: context.getString(R.string.new_york)
    }

}