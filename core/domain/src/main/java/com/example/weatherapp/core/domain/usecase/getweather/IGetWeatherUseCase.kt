package com.example.weatherapp.core.domain.usecase.getweather

import com.example.weatherapp.core.domain_data.model.CityWeather


interface IGetWeatherUseCase {
    suspend fun invoke(
        cityName: String
    ): CityWeather
}