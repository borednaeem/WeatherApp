package com.example.weatherapp.network

import com.example.weatherapp.network.model.CitySearchDto
import com.example.weatherapp.network.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET(ApiConstants.ENDPOINT_DAY_WEATHER)
    suspend fun dayWeather(
        @Query(ApiConstants.PARAM_QUERY) query: String,
        @Query(ApiConstants.PARAM_DAY_COUNT) dayCount: Int,
    ): WeatherDto


    @GET(ApiConstants.ENDPOINT_CITY_AUTOCOMPLETE)
    suspend fun citySearch(
        @Query(ApiConstants.PARAM_QUERY) query: String,
    ): List<CitySearchDto>
}
