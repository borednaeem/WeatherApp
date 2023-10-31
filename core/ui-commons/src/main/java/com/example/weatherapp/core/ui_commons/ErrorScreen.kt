package com.example.swensonhetask.ui.screens.cityweather.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.core.ui_commons.BackgroundColorOverlay
import com.example.weatherapp.core.ui_commons.BackgroundImage
import com.example.weatherapp.core.ui_commons.MediumSpace
import com.example.weatherapp.core.ui_commons.R

@Composable
fun ErrorScreen(
    onRetryButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.error_message),
            style = MaterialTheme.typography.bodyLarge
        )
        MediumSpace()
        Button(onClick = onRetryButtonClicked) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage()
        BackgroundColorOverlay()
        ErrorScreen(
            onRetryButtonClicked = {}
        )
    }
}