package com.example.weatherapp.core.data.repo.impl


import com.example.weatherapp.core.data.mapper.ICityWeatherDataDomainMapper
import com.example.weatherapp.core.data.repo.IWeatherRepo
import com.example.weatherapp.core.domain_data.model.CityWeather
import com.example.weatherapp.network.WeatherApiInterface
import com.example.weatherapp.network.model.WeatherDto

class WeatherRepo(
    private val api: WeatherApiInterface,
    private val weatherMapper: ICityWeatherDataDomainMapper
) : IWeatherRepo {

    override suspend fun cityWeather(cityName: String, dayCount: Int): com.example.weatherapp.core.domain_data.model.CityWeather {
        val weatherResponseData = api.dayWeather(
            query = cityName,
            dayCount = dayCount
        )
        return weatherMapper.mapFromDataToDomain(weatherResponseData)
    }
}