package com.example.weatherapp.core.data.repo

import com.example.weatherapp.core.domain_data.model.City


interface ICityRepo {
    suspend fun searchCities(query: String): List<com.example.weatherapp.core.domain_data.model.City>
}