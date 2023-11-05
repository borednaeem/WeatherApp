package com.example.weatherapp.core.data.repo.impl

import com.example.weatherapp.core.data.mapper.ICitySearchDataDomainMapper
import com.example.weatherapp.core.data.repo.ICityRepo
import com.example.weatherapp.network.WeatherApiInterface

class CityRepo(
    private val api: WeatherApiInterface,
    private val cityMapper: ICitySearchDataDomainMapper
) : ICityRepo {

    override suspend fun searchCities(query: String): List<com.example.weatherapp.core.domain_data.model.City> {
        val citySearchResponseData = api.citySearch(
            query = query
        )
        return citySearchResponseData.map {
            cityMapper.mapFromDataToDomain(it)
        }
    }
}