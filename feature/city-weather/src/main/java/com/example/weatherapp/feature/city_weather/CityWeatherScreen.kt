package com.example.weatherapp.feature.city_weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun CityWeatherScreen(viewModel: CityWeatherViewModel) {
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(key1 = null, block = {
        viewModel.loadDefaultCityWeather()
    })

    CityWeatherContent(
        screenState,
        onSearchClicked = {
            viewModel.onSearchClicked()
        }
    )
}