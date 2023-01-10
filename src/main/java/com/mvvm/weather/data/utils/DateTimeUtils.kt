package com.mvvm.weather.data.utils

import android.text.format.DateFormat
import java.text.ParseException
import java.util.*


object DateTimeUtils {
    var FORMAT_CURRENT = "EEEE MMM d hh:mm a"
    var FORMAT_DAY = "EEE"
    var Format_Month = "MMMM"

    fun convertLongToDateString(time: Long, format: String): String {
        var formattedDate = ""
        try {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = time * 1000L
            formattedDate = DateFormat.format(format, cal).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return formattedDate
    }
}
