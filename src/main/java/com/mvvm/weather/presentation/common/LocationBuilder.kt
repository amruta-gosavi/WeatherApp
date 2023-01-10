package com.mvvm.weather.presentation.common

import android.content.Context
import android.location.Location
import com.mvvm.weather.R
import com.mvvm.weather.data.utils.Constants


object LocationBuilder {

    fun getLocation(selection: String, context: Context): Location {
        when (selection) {
            context.getString(R.string.new_york) -> return Constants.NEW_YORK_LOCATION
            context.getString(R.string.la) -> return Constants.LA_LOCATION
            context.getString(R.string.chicago) -> return Constants.CHICAGO_LOCATION
            context.getString(R.string.houston) -> return Constants.HOUSTON_LOCATION
            context.getString(R.string.sfo) -> return Constants.SFO_LOCATION
            context.getString(R.string.atlanta) -> return Constants.ATLANTA_LOCATION
            context.getString(R.string.boston) -> return Constants.BOSTON_LOCATION
        }
        return Constants.NEW_YORK_LOCATION
    }
}