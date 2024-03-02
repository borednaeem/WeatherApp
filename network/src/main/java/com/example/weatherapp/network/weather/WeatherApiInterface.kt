package com.example.weatherapp.network.weather

import com.example.weatherapp.network.BuildConfig
import com.example.weatherapp.network.model.CitySearchDto
import com.example.weatherapp.network.model.WeatherDto
import com.example.weatherapp.network.model.weather.ForecastData
import com.example.weatherapp.network.model.weather.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET(WeatherApiConstants.ENDPOINT_DAY_WEATHER)
    suspend fun dayWeather(
        @Query(WeatherApiConstants.PARAM_QUERY) query: String,
        @Query(WeatherApiConstants.PARAM_API_KEY) apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): WeatherData

    @GET(WeatherApiConstants.ENDPOINT_FORECAST)
    suspend fun forecast(
        @Query(WeatherApiConstants.PARAM_QUERY) query: String,
        @Query(WeatherApiConstants.PARAM_API_KEY) apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): ForecastData
}
