package com.example.weatherapp.core.data.repo.impl


import com.example.weatherapp.core.data.mapper.ICityWeatherDataDomainMapper
import com.example.weatherapp.core.data.repo.IWeatherRepo
import com.example.weatherapp.core.domain_data.model.CityWeather
import com.example.weatherapp.network.weather.WeatherApiInterface

class WeatherRepo(
    private val api: WeatherApiInterface,
    private val weatherMapper: ICityWeatherDataDomainMapper
) : IWeatherRepo {

    override suspend fun cityWeather(cityName: String, dayCount: Int): CityWeather {
        val weatherResponseData = api.dayWeather(query = cityName)
        val forecastResponseData = api.forecast(query = cityName)
        return weatherMapper.mapFromDataToDomain(weatherResponseData, forecastResponseData)
    }
}