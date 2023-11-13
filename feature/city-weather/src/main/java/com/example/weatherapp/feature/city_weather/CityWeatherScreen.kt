package com.example.weatherapp.feature.city_weather

import androidx.compose.runtime.Composable

@Composable
fun CityWeatherScreen(viewModel: CityWeatherViewModel) {
    val screenState = viewModel.screenState
    CityWeatherContent(
        screenState.value,
        onSearchTextChanged = { newQuery ->
            viewModel.onSearchTextChanged(newQuery)
        },
        onExitSearchClicked = {
            viewModel.onExitSearchClicked()
        },
        onSearchResultClicked = { city ->
            viewModel.onSearchResultClicked(city)
        },
        onSearchClicked = {
            viewModel.onSearchClicked()
        },
        onRetryButtonClick = {
            viewModel.onRetryClicked()
        },
        onSearchClearClicked = {
            viewModel.onSearchCleared()
        }
    )
}