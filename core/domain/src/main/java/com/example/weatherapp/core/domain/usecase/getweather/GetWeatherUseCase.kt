package com.example.weatherapp.core.domain.usecase.getweather

import com.example.weatherapp.core.data.repo.IWeatherRepo
import com.example.weatherapp.core.domain_data.Constants
import com.example.weatherapp.core.domain_data.model.CityWeather


class GetWeatherUseCase(
    private val weatherRepo: IWeatherRepo
) : IGetWeatherUseCase {

    override suspend fun invoke(cityName: String): CityWeather {
        return weatherRepo.cityWeather(cityName, Constants.MAX_FORECAST_DAYS)
    }
}