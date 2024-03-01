package com.example.weatherapp.network.di

import com.example.weatherapp.network.ApiHelper
import com.example.weatherapp.network.citysearch.CitySearchApiInterface
import com.example.weatherapp.network.weather.WeatherApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideWeatherApi(): WeatherApiInterface {
        return ApiHelper.getWeatherApi()
    }

    @Provides
    fun provideCitySearchApi(): CitySearchApiInterface {
        return ApiHelper.getCitySearchApi()
    }
}