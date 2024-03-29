package com.example.weatherapp.feature.city_weather

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.domain_data.Constants
import com.example.weatherapp.core.domain.usecase.getweather.IGetWeatherUseCase
import com.example.weatherapp.core.domain.usecase.searchcity.ISearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val searchCityUseCase: ISearchCityUseCase,
    private val getWeatherUseCase: IGetWeatherUseCase,
) : ViewModel() {

    private val _screenState =
        MutableStateFlow<CityWeatherScreenState>(CityWeatherScreenState.Loading)
    val screenState: StateFlow<CityWeatherScreenState>
        get() = _screenState

    private var updateTimer: Timer? = null

    private var currentCityWeather = com.example.weatherapp.core.domain_data.model.CityWeather()
    private var currentQuery = ""
    private var searchResults: List<com.example.weatherapp.core.domain_data.model.City> = listOf()

    private fun startUpdates() {
        updateTimer = Timer()
        updateTimer?.schedule(object : TimerTask() {
            override fun run() {
                loadWeatherData(currentCityWeather.cityName, ignoreError = true)
            }
        }, Constants.WEATHER_UPDATE_INTERVAL_MILLISECONDS)
    }

    override fun onCleared() {
        stopUpdates()
        super.onCleared()
    }

    private fun stopUpdates() {
        updateTimer?.cancel()
        updateTimer?.purge()
        updateTimer = null
    }

    fun loadDefaultCityWeather() {
        loadWeatherData(Constants.defaultCityName)
    }

    fun onSearchTextChanged(newQuery: String) {
        _screenState.value = CityWeatherScreenState.ShowData(currentCityWeather, true)
        if (newQuery.isNotBlank()) {
            searchCity(newQuery)
        }
    }

    private fun searchCity(newQuery: String) {
        viewModelScope.launch {
            searchCityFlow(newQuery)
                .flowOn(Dispatchers.IO)
                .catch {
                    showError {
                        searchCity(newQuery)
                    }
                }
                .collectLatest {
                    val results = it.take(Constants.MAX_SEARCH_RESULTS)
                    _screenState.value =
                        CityWeatherScreenState.ShowData(currentCityWeather, true, newQuery, results)
                }
        }
    }

    private fun searchCityFlow(newQuery: String) = flow {
        val searchResults = searchCityUseCase.invoke(newQuery)
        emit(searchResults)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun loadWeatherData(cityName: String, ignoreError: Boolean = false) {
        viewModelScope.launch {
            try {
                _screenState.value = CityWeatherScreenState.Loading
                val weatherData = getWeatherUseCase(cityName)
                _screenState.value = CityWeatherScreenState.ShowData(weatherData)
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "")
                _screenState.value = CityWeatherScreenState.Error {
                    loadWeatherData(cityName, ignoreError)
                }
            }
        }
    }

    private fun showError(runnable: Runnable) {
        _screenState.value = CityWeatherScreenState.Error(runnable)
    }

    fun onSearchClicked() {
        searchResults = listOf()
        _screenState.value = CityWeatherScreenState.ShowData(
            currentCityWeather,
            true,
            currentQuery,
            searchResults
        )
    }

    companion object {
        private const val TAG = "CityWeatherViewModel"
    }
}