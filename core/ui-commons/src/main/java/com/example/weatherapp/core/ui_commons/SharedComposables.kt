package com.example.weatherapp.core.ui_commons


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun BackgroundColorOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            //.background(appBackground)
    )
}

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(R.drawable.weather_bg),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ExtraSmallSpace() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.extra_small_space).value.dp))
}

@Composable
fun SmallSpace() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.small_space).value.dp))
}

@Composable
fun MediumSpace() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.medium_space).value.dp))
}

@Composable
fun LargeSpace() {
    Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.large_space).value.dp))
}


