package com.example.weatherapp.core.domain.usecase.searchcity

import com.example.weatherapp.core.data.repo.ICityRepo
import com.example.weatherapp.core.domain.usecase.searchcity.ISearchCityUseCase
import com.example.weatherapp.core.domain_data.model.City

class SearchCityUseCase(
    private val cityRepo: ICityRepo
): ISearchCityUseCase {

    override suspend fun invoke(query: String): List<City> {
        return cityRepo.searchCities(query)
    }
}