package com.example.weatherapp.network.model.weather

data class ForecastData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherData>,
    val message: Int
)