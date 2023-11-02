package com.example.weatherapp.core.data.mapper

import com.example.weatherapp.core.domain_data.Constants
import com.example.weatherapp.core.domain_data.model.CityWeather
import com.example.weatherapp.core.domain_data.model.DayWeatherSummary
import com.example.weatherapp.network.model.WeatherDto
import com.example.weatherapp.util.DateTimeHelper
import com.example.weatherapp.util.getDayName
import com.example.weatherapp.util.getFormattedDate
import com.example.weatherapp.util.getFormattedTime

interface ICityWeatherDataDomainMapper {
    
    fun mapFromDataToDomain(
        weatherDto: WeatherDto
    ) : CityWeather

    fun mapFromDomainToData(
        weather: CityWeather
    ) : WeatherDto
}

class CityWeatherDataDomainMapper : ICityWeatherDataDomainMapper {
    override fun mapFromDataToDomain(weatherDto: WeatherDto): CityWeather {
        val localDate = DateTimeHelper.dateFromFormat(
            weatherDto.location?.localtime ?: "",
            Constants.API_TIME_FORMAT
        )
        return CityWeather(
            cityName = weatherDto.location?.name ?: "",
            date = localDate?.getFormattedDate() ?: "",
            time = localDate?.getFormattedTime() ?: "",
            summaryItems = weatherDto.forecast?.forecastday?.mapIndexed { index, forecastdayItem ->
                val forecastDate = DateTimeHelper.dateFromFormat(
                    forecastdayItem?.date ?: "",
                    Constants.API_DATE_FORMAT
                )
                DayWeatherSummary(
                    weatherImage = "https:${forecastdayItem?.day?.condition?.icon}",
                    highTempInFahrenheit = forecastdayItem?.day?.maxtempF?.toFloat() ?: -1f,
                    lowTempInFahrenheit = forecastdayItem?.day?.mintempF?.toFloat() ?: -1f,
                    isToday = index == 0,
                    isTomorrow = index == 1,
                    dayName = forecastDate?.getDayName() ?: "",
                )
            } ?: listOf(),
            weatherDescription = weatherDto.current?.condition?.text ?: "",
            temperatureInFahrenheit = weatherDto.current?.tempF?.toFloat() ?: 0f,
            temperatureImage = "https:${weatherDto.current?.condition?.icon}",
            windSpeed = weatherDto.current?.windMph?.toFloat() ?: -1f,
            humidityPercentage = weatherDto.current?.humidity ?: 0,
        )
    }

    override fun mapFromDomainToData(weather: CityWeather): WeatherDto {
        TODO("Not yet implemented")
    }

}