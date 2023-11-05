package com.example.weatherapp.core.data.repo

import com.example.weatherapp.core.domain_data.model.CityWeather

interface IWeatherRepo {
    suspend fun cityWeather(
        cityName: String,
        dayCount: Int
    ): CityWeather
}