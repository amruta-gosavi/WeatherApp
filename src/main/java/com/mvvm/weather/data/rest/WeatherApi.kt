package com.mvvm.weather.data.rest


import com.mvvm.weather.data.rest.model.DarkskyModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface WeatherApi {

    @GET("forecast/{key}/{latitude},{longitude}")
    suspend fun getForecast(
        @HeaderMap headers: Map<String, String>,
        @Path(value = "key") key: String,
        @Path(value = "latitude") latitude: String,
        @Path(value = "longitude") longitude: String
    ): Response<DarkskyModel.Darksky>

}