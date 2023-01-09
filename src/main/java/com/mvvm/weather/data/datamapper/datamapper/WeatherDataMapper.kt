package com.mvvm.weather.data.datamapper.datamapper

import com.mvvm.weather.presentation.common.WeatherUnits
import com.mvvm.weather.data.rest.model.DarkskyModel
import com.mvvm.weather.data.utils.DateTimeUtils
import com.mvvm.weather.presentation.model.DisplayableWeatherData

class WeatherDataMapper {

    /**
     * Apply business rules and transform model into displayable viewmodel
     * Process weather data and apply units/rules as needed
     *
     * @param EntityCollection Object Collection to be transformed.
     * @return
     */

    fun transform(item: DarkskyModel.Darksky): DisplayableWeatherData.DisplayableDarkSky {
        var currently = transformDisplayableData(item.currently)

        var displayableData: MutableList<DisplayableWeatherData.DisplayableData> =
            ArrayList<DisplayableWeatherData.DisplayableData>()

        if (!item.daily.data.isNullOrEmpty()) {
            for (dataItem in item.daily.data) {
                var data = transformDisplayableData(dataItem)
                displayableData.add(data)
            }

        }
        var daily = DisplayableWeatherData.DisplayableDaily(
            item.daily.summary,
            item.daily.icon,
            displayableData
        )

        return DisplayableWeatherData.DisplayableDarkSky(
            currently, daily
        )
    }

    private fun transformDisplayableData(item: DarkskyModel.Data): DisplayableWeatherData.DisplayableData {
        return DisplayableWeatherData.DisplayableData(
            getTimeToDateString(item.time),
            item.summary,
            item.icon,
            getTemperature(item.temperature, item.apparentTemperatureHigh),
            item.dewPoint?.toString(),
            getHumidity(item.humidity),
            getWindSpeed(item.windSpeed),
            getCloudCover(item.cloudCover),
            getUVIndex(item.uvIndex),
            "" + item.visibility,
            "" + item.ozone,
            getPrecipitationProbability(item.precipProbability),
            getDay(item.time),
            getGusts(item.windGust),
            getSunrise(item.sunriseTime),
            getSunset(item.sunsetTime),
            getPrecipIntensity(item.precipIntensity),
            getPressure(item.pressure),
            getMonth(item.time)
        )
    }

    fun transformFromDBData(dbData: List<DarkskyModel.Data>): DisplayableWeatherData.DisplayableDarkSky {
        var displayableData: MutableList<DisplayableWeatherData.DisplayableData> =
            ArrayList<DisplayableWeatherData.DisplayableData>()

        if (!dbData.isNullOrEmpty()) {
            for (dataItem in dbData) {
                var data = transformDisplayableData(dataItem)
                displayableData.add(data)
            }
        }

        var daily = DisplayableWeatherData.DisplayableDaily(
            displayableData.get(0).summary,
            displayableData.get(0).icon,
            displayableData as MutableList<DisplayableWeatherData.DisplayableData>
        )

        var currently = displayableData.get(0)
        var displayableModel = DisplayableWeatherData.DisplayableDarkSky(currently, daily)

        return displayableModel
    }


    private fun getTimeToDateString(time: Long): String? {
        return DateTimeUtils.convertLongToDateString(time, DateTimeUtils.FORMAT_CURRENT)
    }

    private fun getTemperature(temp: Double?, apparentHigh: Double?): String? {
        var temperature = if (temp == null) {
            "$apparentHigh \u2109"
        } else
            "$temp \u2109"

        return temperature
    }

    private fun getHumidity(humidity: Double?): String? {
        var humidityPercent = humidity?.times(100)
        return convertDoubleTwoDecimalPoints("" + humidityPercent) + WeatherUnits.PERCENT.value
    }

    private fun getWindSpeed(wind: Double?): String? {
        return convertDoubleTwoDecimalPoints("" + wind) + " " + WeatherUnits.MPH.value
    }

    private fun getCloudCover(cloudCover: Double?): String? {
        var cloudPercent = cloudCover?.times(100)
        return convertDoubleTwoDecimalPoints("" + cloudPercent) + WeatherUnits.PERCENT.value
    }

    private fun getPrecipitationProbability(precip: Double?): String? {
        var cloudPercent = precip?.times(100)
        return "" + convertDoubleTwoDecimalPoints("" + cloudPercent) + WeatherUnits.PERCENT.value
    }

    private fun convertDoubleTwoDecimalPoints(number: String?): String {
        val d = number?.toDouble()
        return "%.2f".format(d)
    }

    private fun getUVIndex(uvIndex: Int?): String? {
        var uvIndexDesc = WeatherUnits.NO_UNIT
        if (uvIndex != null) {
            if (uvIndex > 6) uvIndexDesc = WeatherUnits.HIGH
            else if (uvIndex == 5) uvIndexDesc = WeatherUnits.MODERATE
            else if (uvIndex < 5) uvIndexDesc = WeatherUnits.LOW
        }

        return "UV Index:  $uvIndexDesc"
    }

    private fun getDay(time: Long): String? {
        var timeString: String? = null
        DateTimeUtils.convertLongToDateString(time, DateTimeUtils.FORMAT_DAY)
            .also { timeString = it }
        return timeString
    }

    private fun getMonth(time: Long): String? {
        return DateTimeUtils.convertLongToDateString(time, DateTimeUtils.Format_Month)
    }

    private fun getGusts(gustVal: Double?): String? {
        return "" + convertDoubleTwoDecimalPoints("" + gustVal?.times(100))
    }

    private fun getSunrise(time: Long?): String? {
        return time?.let { DateTimeUtils.convertLongToDateString(it, DateTimeUtils.FORMAT_CURRENT) }
    }


    private fun getSunset(time: Long?): String? {
        return time?.let { DateTimeUtils.convertLongToDateString(it, DateTimeUtils.FORMAT_CURRENT) }
    }


    private fun getPrecipIntensity(precip: Double?): String? {
        var cloudPercent = precip?.times(100)
        return "" + convertDoubleTwoDecimalPoints("" + cloudPercent)
    }

    private fun getPressure(pressure: Double?): String? {
        var cloudPercent = pressure?.times(100)
        return "" + convertDoubleTwoDecimalPoints("" + cloudPercent)
    }

}