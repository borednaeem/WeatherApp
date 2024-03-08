package com.example.weatherapp.core.data.di

import com.example.weatherapp.core.data.mapper.CitySearchDataDomainMapper
import com.example.weatherapp.core.data.mapper.CityWeatherDataDomainMapper
import com.example.weatherapp.core.data.mapper.ICitySearchDataDomainMapper
import com.example.weatherapp.core.data.mapper.ICityWeatherDataDomainMapper
import com.example.weatherapp.core.data.repo.ICityRepo
import com.example.weatherapp.core.data.repo.IWeatherRepo
import com.example.weatherapp.core.data.repo.impl.CityRepo
import com.example.weatherapp.core.data.repo.impl.WeatherRepo
import com.example.weatherapp.network.citysearch.CitySearchApiInterface
import com.example.weatherapp.network.weather.WeatherApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {

    @Provides
    fun provideCitySearchDataDomainMapper(): ICitySearchDataDomainMapper {
        return CitySearchDataDomainMapper()
    }

    @Provides
    fun provideCityWeatherDataDomainMapper(): ICityWeatherDataDomainMapper {
        return CityWeatherDataDomainMapper()
    }

    @Provides
    fun provideCityRepo(
        citySearchApi: CitySearchApiInterface,
        citySearchMapper: ICitySearchDataDomainMapper
    ): ICityRepo {
        return CityRepo(
            api = citySearchApi,
            cityMapper = citySearchMapper
        )
    }

    @Provides
    fun provideWeatherRepo(
        weatherApi: WeatherApiInterface,
        cityWeatherMapper: ICityWeatherDataDomainMapper
    ): IWeatherRepo {
        return WeatherRepo(
            api = weatherApi,
            weatherMapper = cityWeatherMapper
        )
    }
}