package com.example.weatherapp.core.domain_data.model

data class DayWeatherSummary(
    val weatherImage: String = "",
    val highTempInFahrenheit: Float = 0f,
    val lowTempInFahrenheit: Float = 0f,
    val dayName: String = "",
    val isToday: Boolean = false,
    val isTomorrow: Boolean = false
)