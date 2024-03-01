package com.example.weatherapp.network.citysearch

import com.example.weatherapp.network.model.CitySearchDto
import com.example.weatherapp.network.weather.WeatherApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface CitySearchApiInterface {
    @GET(CitySearchApiConstants.ENDPOINT_SEARCH)
    suspend fun citySearch(
        @Query(CitySearchApiConstants.PARAM_KEYWORD) keyword: String,
        @Query(CitySearchApiConstants.PARAM_MAX_RESULTS) max: Int = CitySearchApiConstants.MAX_RESULTS,
    ): List<CitySearchDto>
}