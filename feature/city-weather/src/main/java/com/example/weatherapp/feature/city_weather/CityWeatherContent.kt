package com.example.weatherapp.feature.city_weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.swensonhetask.ui.screens.cityweather.composables.*
import com.example.weatherapp.core.ui_commons.BackgroundColorOverlay
import com.example.weatherapp.core.ui_commons.BackgroundImage
import com.example.weatherapp.feature.city_weather.composables.ShowDataScreen
import com.example.weatherapp.core.domain_data.model.City
import com.example.weatherapp.core.ui_commons.LoadingScreen


@Composable
fun CityWeatherContent(
    screenState: CityWeatherScreenState,
    onSearchClicked: () -> Unit,
    onSearchResultClicked: (City) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onExitSearchClicked: () -> Unit,
    onRetryButtonClick: () -> Unit,
    onSearchClearClicked: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        BackgroundColorOverlay()
        CityWeatherContentViews(
            screenState, onSearchClicked, onSearchResultClicked,
            onSearchTextChanged, onExitSearchClicked, onRetryButtonClick, onSearchClearClicked
        )
    }
}

@Composable
fun CityWeatherContentViews(
    screenState: CityWeatherScreenState,
    onSearchClicked: () -> Unit,
    onSearchResultClicked: (City) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onExitSearchClicked: () -> Unit,
    onRetryButtonClick: () -> Unit,
    onSearchClearClicked: () -> Unit,
) {
    when (screenState) {
        is CityWeatherScreenState.Error -> {
            ErrorScreen(
                onRetryButtonClicked = onRetryButtonClick
            )
        }
        CityWeatherScreenState.Loading -> {
            LoadingScreen()
        }

        is CityWeatherScreenState.ShowData -> {
            ShowDataScreen(
                cityWeather = screenState.cityWeather,
                onSearchClicked = onSearchClicked,
                onSearchResultClicked = onSearchResultClicked,
                onSearchTextChanged = onSearchTextChanged,
                onExitSearchClicked = onExitSearchClicked,
                searchResults = screenState.searchResults,
                searchShown = screenState.searchShown,
                onSearchClearClicked = onSearchClearClicked
            )
        }
    }
}
