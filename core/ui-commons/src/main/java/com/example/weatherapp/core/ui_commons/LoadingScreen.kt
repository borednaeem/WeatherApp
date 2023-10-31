package com.example.swensonhetask.ui.screens.cityweather.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.core.ui_commons.BackgroundColorOverlay
import com.example.weatherapp.core.ui_commons.BackgroundImage


@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color.White
        )
    }
}

@Preview
@Composable
fun LoadingScreenPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()
        BackgroundColorOverlay()
        LoadingScreen()
    }
}