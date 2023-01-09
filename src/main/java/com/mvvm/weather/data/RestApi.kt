package com.mvvm.weather.data

import com.mvvm.weather.data.rest.WeatherApi
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Build
import kotlin.jvm.Volatile
import java.util.LinkedHashMap

class RestApi private constructor() {
    private val REST_ENDPOINT = BuildConfig.Endpoint
    var weatherApi: WeatherApi? = null
        get() {
            initRetrofit()
            if (field == null) {
                field = retrofit?.create(WeatherApi::class.java)
            }
            return field
        }
        private set
    private var retrofit: Retrofit? = null
    private val OS_VERSION = androidVersion
    private val VERSION_NAME = BuildConfig.VERSION_NAME
    private val USER_AGENT = "Android;$OS_VERSION;$VERSION_NAME"

    private fun initRetrofit() {
        if (retrofit == null) {
            // initUserAgent();
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                    .addInterceptor(UserAgentInterceptor(USER_AGENT)).addInterceptor(interceptor)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(REST_ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            } else {
                retrofit = Retrofit.Builder()
                    .baseUrl(REST_ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }

    private val headers: MutableMap<String, String> = LinkedHashMap()
    fun getHeaders(): Map<String, String> {
        return headers
    }

    private fun createHeaders() {
        headers["Accept"] = "*/*"
    }

    val androidVersion: String
        get() = Build.VERSION.RELEASE

    companion object {
        @Volatile
        var instance: RestApi? = null
            get() {
                if (field == null) {
                    synchronized(RestApi::class.java) {
                        if (field == null) {
                            field = RestApi()
                        }
                    }
                }
                return field
            }
            private set
    }

    init {
        initRetrofit()
        createHeaders()
    }
}