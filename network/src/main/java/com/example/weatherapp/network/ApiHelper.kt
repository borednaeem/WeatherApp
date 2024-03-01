package com.example.weatherapp.network

import com.example.weatherapp.network.citysearch.CitySearchApiConstants
import com.example.weatherapp.network.citysearch.CitySearchApiInterface
import com.example.weatherapp.network.weather.WeatherApiConstants
import com.example.weatherapp.network.weather.WeatherApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiHelper {

    private fun getInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()
    }

    fun getWeatherApi(): WeatherApiInterface {
        return getInstance(WeatherApiConstants.BASE_URL)
            .create(WeatherApiInterface::class.java)
    }

    fun getCitySearchApi(): CitySearchApiInterface {
        return getInstance(CitySearchApiConstants.BASE_URL)
            .create(CitySearchApiInterface::class.java)
    }
}