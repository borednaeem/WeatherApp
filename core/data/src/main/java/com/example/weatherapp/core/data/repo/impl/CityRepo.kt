package com.example.weatherapp.core.data.repo.impl

import com.example.weatherapp.core.data.mapper.ICitySearchDataDomainMapper
import com.example.weatherapp.core.data.repo.ICityRepo
import com.example.weatherapp.network.citysearch.CitySearchApiInterface
import com.example.weatherapp.network.weather.WeatherApiInterface

class CityRepo(
    private val api: CitySearchApiInterface,
    private val cityMapper: ICitySearchDataDomainMapper
) : ICityRepo {

    override suspend fun searchCities(query: String): List<com.example.weatherapp.core.domain_data.model.City> {
        val citySearchResponseData = api.citySearch(
            keyword = query
        )
        return citySearchResponseData.map {
            cityMapper.mapFromDataToDomain(it)
        }
    }
}