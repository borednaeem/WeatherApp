package com.example.weatherapp

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApp : MultiDexApplication() {
}