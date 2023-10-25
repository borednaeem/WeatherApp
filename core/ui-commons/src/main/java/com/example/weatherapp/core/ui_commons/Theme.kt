package com.example.weatherapp.core.ui_commons

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme

val LightAndroidColorScheme = lightColorScheme(
    primary = Blue400,
    onPrimary = Color.White,
    primaryContainer = Blue900,
    onPrimaryContainer = Blue100,
    secondary = Blue400,
    onSecondary = Color.White,
    secondaryContainer = Blue900,
    onSecondaryContainer = Blue100,
    tertiary = Red400,
    onTertiary = Color.White,
    tertiaryContainer = Red900,
    onTertiaryContainer = Red100,
    error = Red400,
    onError = Color.White,
    errorContainer = Red900,
    onErrorContainer = Red100,
    background = BlueGrey900,
    onBackground = BlueGrey100,
    surface = BlueGrey900,
    onSurface = BlueGrey100,
    surfaceVariant = BlueGrey100,
    onSurfaceVariant = BlueGrey300,
    inverseSurface = BlueGrey200,
    inverseOnSurface = BlueGrey900,
    outline = BlueGrey500,
)

val DarkAndroidColorScheme = darkColorScheme(
    primary = Blue800,
    onPrimary = Blue200,
    primaryContainer = Blue300,
    onPrimaryContainer = Blue900,
    secondary = Blue800,
    onSecondary = Blue200,
    secondaryContainer = Blue300,
    onSecondaryContainer = Blue900,
    tertiary = Red800,
    onTertiary = Red200,
    tertiaryContainer = Red300,
    onTertiaryContainer = Red900,
    error = Red800,
    onError = Red200,
    errorContainer = Red300,
    onErrorContainer = Red900,
    background = BlueGrey100,
    onBackground = BlueGrey900,
    surface = BlueGrey100,
    onSurface = BlueGrey900,
    surfaceVariant = BlueGrey300,
    onSurfaceVariant = BlueGrey800,
    inverseSurface = BlueGrey900,
    inverseOnSurface = BlueGrey100,
    outline = BlueGrey600,
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
