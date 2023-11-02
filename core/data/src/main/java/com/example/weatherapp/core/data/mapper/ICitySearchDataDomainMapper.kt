package com.example.weatherapp.core.data.mapper

import com.example.weatherapp.core.domain_data.model.City
import com.example.weatherapp.network.model.CitySearchDto

interface ICitySearchDataDomainMapper {

    fun mapFromDataToDomain(
        citySearchDto: CitySearchDto
    ): City

    fun mapFromDomainToData(city: City): CitySearchDto
}

class CitySearchDataDomainMapper : ICitySearchDataDomainMapper {
    override fun mapFromDataToDomain(citySearchDto: CitySearchDto): City {
        return City(
            name = citySearchDto.name ?: "",
            region = citySearchDto.region ?: ""
        )
    }

    override fun mapFromDomainToData(city: City): CitySearchDto {
        return CitySearchDto(
            name = city.name,
            region = city.region
        )
    }

}