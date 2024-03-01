package com.example.weatherapp.feature.city_weather

import androidx.compose.runtime.Composable

@Composable
fun CityWeatherScreen(viewModel: CityWeatherViewModel) {
    val screenState = viewModel.screenState
    CityWeatherContent(
        screenState.value,
        onSearchClicked = {
            viewModel.onSearchClicked()
        }
    )
}