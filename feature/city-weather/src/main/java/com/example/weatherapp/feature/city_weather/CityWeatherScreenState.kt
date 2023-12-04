package com.example.weatherapp.feature.city_weather

import com.example.weatherapp.core.domain_data.model.City
import com.example.weatherapp.core.domain_data.model.CityWeather

sealed class CityWeatherScreenState {
    object Loading : CityWeatherScreenState()

    class Error(
        val onRetryClick: Runnable
    ) : CityWeatherScreenState()

    class ShowData(
        val cityWeather: CityWeather,
        val searchShown: Boolean = false,
        val query: String = "",
        val searchResults: List<City> = listOf()
    ) : CityWeatherScreenState()
}