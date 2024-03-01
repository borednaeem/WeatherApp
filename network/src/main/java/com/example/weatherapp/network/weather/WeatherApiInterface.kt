package com.example.weatherapp.network.weather

import com.example.weatherapp.network.BuildConfig
import com.example.weatherapp.network.model.CitySearchDto
import com.example.weatherapp.network.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET(WeatherApiConstants.ENDPOINT_DAY_WEATHER)
    suspend fun dayWeather(
        @Query(WeatherApiConstants.PARAM_QUERY) query: String,
        @Query(WeatherApiConstants.PARAM_API_KEY) apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): WeatherDto
}
