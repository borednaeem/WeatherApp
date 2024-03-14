package com.example.weatherapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.metrics.performance.JankStats
import androidx.metrics.performance.PerformanceMetricsState
import com.example.weatherapp.core.ui_commons.WeatherAppTheme
import com.example.weatherapp.feature.city_weather.CityWeatherScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var jankStates: dagger.Lazy<JankStats>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            WeatherAppTheme {
                Surface {
                    CityWeatherScreen(
                        viewModel = hiltViewModel()
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        jankStates.get().isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        jankStates.get().isTrackingEnabled = false
    }
}