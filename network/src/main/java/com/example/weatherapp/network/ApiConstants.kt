package com.example.weatherapp.network

object ApiConstants {
    const val BASE_URL = "https://api.weatherapi.com/v1/"

    const val ENDPOINT_DAY_WEATHER = "forecast.json"
    const val ENDPOINT_CITY_AUTOCOMPLETE = "search.json"

    const val PARAM_KEY = "key"
    const val PARAM_QUERY = "q"
    const val PARAM_DAY_COUNT = "days"
}