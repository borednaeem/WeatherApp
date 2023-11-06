package com.example.weatherapp.core.domain.usecase.searchcity

import com.example.weatherapp.core.domain_data.model.City


interface ISearchCityUseCase {
    suspend fun invoke(query: String): List<com.example.weatherapp.core.domain_data.model.City>
}