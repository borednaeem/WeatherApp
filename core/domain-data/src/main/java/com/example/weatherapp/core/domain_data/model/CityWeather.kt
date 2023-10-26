package com.example.weatherapp.core.domain_data.model

data class CityWeather(
    val cityName: String = "",
    val date: String = "",
    val time: String = "",
    val temperatureInFahrenheit: Float = 0f,
    val temperatureImage: String = "",
    val weatherDescription: String = "",
    val windSpeed: Float = 0f,
    val humidityPercentage: Int = 0,
    val summaryItems: List<DayWeatherSummary> = arrayListOf()
)