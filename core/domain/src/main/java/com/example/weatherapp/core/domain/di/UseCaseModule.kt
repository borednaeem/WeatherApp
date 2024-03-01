package com.example.weatherapp.core.domain.di

import androidx.lifecycle.ViewModel
import com.example.weatherapp.core.data.repo.ICityRepo
import com.example.weatherapp.core.data.repo.IWeatherRepo
import com.example.weatherapp.core.data.repo.impl.CityRepo
import com.example.weatherapp.core.data.repo.impl.WeatherRepo
import com.example.weatherapp.core.domain.usecase.getweather.GetWeatherUseCase
import com.example.weatherapp.core.domain.usecase.getweather.IGetWeatherUseCase
import com.example.weatherapp.core.domain.usecase.searchcity.ISearchCityUseCase
import com.example.weatherapp.core.domain.usecase.searchcity.SearchCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideGetWeatherUseCase(weatherRepo: IWeatherRepo): IGetWeatherUseCase {
        return GetWeatherUseCase(
            weatherRepo = weatherRepo
        )
    }

    @Provides
    fun provideSearchCityUseCase(cityRepo: ICityRepo): ISearchCityUseCase {
        return SearchCityUseCase(
            cityRepo = cityRepo
        )
    }
}