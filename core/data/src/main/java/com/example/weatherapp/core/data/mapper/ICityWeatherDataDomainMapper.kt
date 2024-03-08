package com.example.weatherapp.core.data.mapper

import com.example.weatherapp.core.domain_data.Constants
import com.example.weatherapp.core.domain_data.model.CityWeather
import com.example.weatherapp.core.domain_data.model.DayWeatherSummary
import com.example.weatherapp.network.model.WeatherDto
import com.example.weatherapp.network.model.weather.ForecastData
import com.example.weatherapp.network.model.weather.WeatherData
import com.example.weatherapp.util.DateTimeHelper
import com.example.weatherapp.util.getDayName
import com.example.weatherapp.util.getFormattedDate
import com.example.weatherapp.util.getFormattedTime
import java.util.Date
import kotlin.math.max
import kotlin.math.min

interface ICityWeatherDataDomainMapper {
    
    fun mapFromDataToDomain(
        weatherDto: WeatherData,
        forecastData: ForecastData
    ) : CityWeather

    fun mapFromDomainToData(
        weather: CityWeather
    ) : WeatherDto
}

class CityWeatherDataDomainMapper : ICityWeatherDataDomainMapper {

    companion object {
        const val MAX_SUMMARY_ITEMS = 4
    }
    override fun mapFromDataToDomain(
        weatherDto: WeatherData,
        forecastData: ForecastData
    ): CityWeather {
        val localDate = ""/*DateTimeHelper.dateFromFormat(
            weatherDto.location?.localtime ?: "",
            Constants.API_TIME_FORMAT
        )*/
        val weather = weatherDto.weather.firstOrNull()
        return CityWeather(
            date = DateTimeHelper.dateFromTimestamp(weatherDto.dateTimestamp) ?: "",
            cityName = "${weatherDto.name}, ${weatherDto.sys.country}",
            summaryItems = mapForecastData(forecastData),
            weatherDescription = weather?.description ?: "",
            temperatureInFahrenheit = weatherDto.main.temp.toFloat(),
            temperatureImage = "https://openweathermap.org/img/wn/${weather?.icon}@4x.png",
            windSpeed = weatherDto.wind.speed.toFloat(),
            humidityPercentage = weatherDto.main.humidity,
        )
    }

    private fun mapForecastData(forecastData: ForecastData): List<DayWeatherSummary> {
        val summaryItems = forecastData.list.mapIndexed { index, forecastdayItem ->
            val weather = forecastdayItem.weather.firstOrNull()
            DayWeatherSummary(
                weatherImage = "https://openweathermap.org/img/wn/${weather?.icon}@4x.png",
                highTempInFahrenheit = forecastdayItem.main.temp_max.toFloat(),
                lowTempInFahrenheit = forecastdayItem.main.temp_min.toFloat(),
                isToday = index == 0,
                isTomorrow = index == 1,
                dayName = Date(forecastdayItem.dateTimestamp*1000L).getDayName(),
            )
        }
        return summaryItems.subList(0, min(summaryItems.size, MAX_SUMMARY_ITEMS))
    }

    override fun mapFromDomainToData(weather: CityWeather): WeatherDto {
        TODO("Not yet implemented")
    }

}