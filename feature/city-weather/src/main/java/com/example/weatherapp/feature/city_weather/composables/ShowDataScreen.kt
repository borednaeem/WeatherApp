package com.example.weatherapp.feature.city_weather.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.core.ui_commons.R
import com.example.weatherapp.core.ui_commons.ExtraSmallSpace
import com.example.weatherapp.core.ui_commons.LargeSpace
import com.example.weatherapp.core.ui_commons.MediumSpace
import com.example.weatherapp.core.domain_data.model.City
import com.example.weatherapp.core.domain_data.model.CityWeather
import com.example.weatherapp.core.domain_data.model.DayWeatherSummary

@Composable
fun ShowDataScreen(
    cityWeather: CityWeather,
) {
    WeatherDataWidgets(
        cityWeather = cityWeather,
    )
}

@Composable
fun WeatherDataWidgets(
    cityWeather: CityWeather,
) {

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (cityAndDateSection, temperatureInfoSection, daySummarySection) = createRefs()

        val cityAndDateSectionTopGuideline = createGuidelineFromTop(0.15f)
        val cityAndDateSectionBottomGuideline = createGuidelineFromTop(0.30f)

        val temperatureInfoSectionTopGuideline = createGuidelineFromTop(0.31f)
        val temperatureInfoSectionBottomGuideline = createGuidelineFromTop(0.60f)

        val daySummarySectionTopGuideline = createGuidelineFromTop(0.65f)
        val daySummarySectionBottomGuideline = createGuidelineFromTop(0.90f)

        CityAndDateSection(
            modifier = Modifier
                .constrainAs(cityAndDateSection) {
                    top.linkTo(cityAndDateSectionTopGuideline)
                    bottom.linkTo(cityAndDateSectionBottomGuideline)
                    height = Dimension.fillToConstraints
                },
            cityName = cityWeather.cityName,
            todayDate = cityWeather.date
        )

        TemperatureInfoSection(
            modifier = Modifier
                .constrainAs(temperatureInfoSection) {
                    top.linkTo(temperatureInfoSectionTopGuideline)
                    bottom.linkTo(temperatureInfoSectionBottomGuideline)
                    height = Dimension.fillToConstraints
                },
            currentTemp = cityWeather.temperatureInFahrenheit,
            image = cityWeather.temperatureImage,
            description = cityWeather.weatherDescription,
            windSpeed = stringResource(id = R.string.wind_speed_mph, cityWeather.windSpeed),
            humidity = stringResource(
                id = R.string.humidity_percentage,
                cityWeather.humidityPercentage
            )
        )

        DaySummarySection(
            modifier = Modifier
                .constrainAs(daySummarySection) {
                    top.linkTo(daySummarySectionTopGuideline)
                    bottom.linkTo(daySummarySectionBottomGuideline)
                    height = Dimension.fillToConstraints
                },
            daySummaryItems = cityWeather.summaryItems
        )
    }
}

@Composable
fun DaySummarySection(modifier: Modifier, daySummaryItems: List<DayWeatherSummary>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        items(daySummaryItems.size) { index ->
            val itemData = daySummaryItems[index]
            DaySummaryItem(
                itemData,
                modifier = Modifier
                    .background(
                        if (index % 2 == 0) {
                            Color.Transparent
                        } else {
                            Color(0x61000000)
                        }
                    )
                    .padding(horizontal = 24.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
fun DaySummaryItem(
    item: DayWeatherSummary,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.4f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = when {
                    item.isToday -> stringResource(id = R.string.today)
                    item.isTomorrow -> stringResource(id = R.string.tomorrow)
                    else -> item.dayName
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
            MediumSpace()
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.weatherImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.sun),
                contentDescription = stringResource(id = R.string.content_description_temp_image),
                modifier = Modifier.size(40.dp)
            )
        }
        Text(
            text = stringResource(
                R.string.high_low_temp,
                item.highTempInFahrenheit,
                item.lowTempInFahrenheit
            ),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(0.6f),
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
fun DaySummarySectionPreview() {
    DaySummarySection(
        modifier = Modifier,
        daySummaryItems = listOf(
            DayWeatherSummary(
                weatherImage = "",
                highTempInFahrenheit = 88f,
                lowTempInFahrenheit = 80f,
                dayName = "Today"
            ),

            DayWeatherSummary(
                weatherImage = "",
                highTempInFahrenheit = 88f,
                lowTempInFahrenheit = 80f,
                dayName = "Today"
            ),

            DayWeatherSummary(
                weatherImage = "",
                highTempInFahrenheit = 88f,
                lowTempInFahrenheit = 80f,
                dayName = "Today"
            )
        ),
    )
}

@Composable
fun TemperatureInfoSection(
    modifier: Modifier,
    currentTemp: Float,
    image: String,
    description: String,
    humidity: String,
    windSpeed: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.sun),
            contentDescription = stringResource(id = R.string.content_description_temp_image),
            modifier = Modifier.size(dimensionResource(id = R.dimen.main_weather_icon_size).value.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = currentTemp.toString(),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = stringResource(id = R.string.Fahrenheit),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Thin
                )
            )
        }
        MediumSpace()
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge
        )
        LargeSpace()
        WindAndHumidity(
            wind = windSpeed,
            humidity = humidity
        )
    }
}

@Preview
@Composable
fun TemperatureInfoSectionPreview() {
    TemperatureInfoSection(
        modifier = Modifier,
        currentTemp = 83f,
        description = "It's a sunny day",
        humidity = "60%",
        windSpeed = "60 mph",
        image = ""
    )
}

@Composable
fun WindAndHumidity(wind: String, humidity: String) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        ImageTextItem(R.drawable.wind, wind)
        LargeSpace()
        ImageTextItem(R.drawable.icon_humidity, humidity)
    }
}

@Composable
fun ImageTextItem(image: Int, text: String) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.content_description_temp_image),
            modifier = Modifier.size(dimensionResource(id = R.dimen.small_icon_size).value.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        ExtraSmallSpace()
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun CityAndDateSection(modifier: Modifier, cityName: String, todayDate: String) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = cityName,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        MediumSpace()
        Text(
            text = todayDate,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
fun CityAndDateSectionPreview() {
    CityAndDateSection(
        modifier = Modifier,
        cityName = "San Francisco",
        todayDate = "Saturday, 20 Aug 2022"
    )
}

@Composable
fun SearchInputSection(
    modifier: Modifier,
    onSearchResultClicked: (City) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    onExitSearchClicked: () -> Unit,
    searchResults: List<City>,
    onSearchClearClicked: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(
            bottomEnd = dimensionResource(id = R.dimen.search_card_bottom_radius).value.dp,
            bottomStart = dimensionResource(id = R.dimen.search_card_bottom_radius).value.dp
        ),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LargeSpace()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBackButton(onExitSearchClicked)
                MediumSpace()
                SearchCityTextInput(
                    onSearchTextChanged,
                    onSearchClearClicked
                )
            }
            SearchResultList(
                searchResults,
                onSearchResultClicked
            )
            Spacer(modifier = Modifier.weight(1.0f))
            if (searchResults.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                        //.background(appLightGrey),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowUp,
                        //tint = appBlue,
                        contentDescription = stringResource(id = R.string.content_description_arrow_up),
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBackButton(onExitSearchClicked: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.normal_icon_size).value.dp),
        onClick = onExitSearchClicked
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.content_description_search),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchCityTextInput(
    onSearchTextChanged: (String) -> Unit,
    onSearchClearClicked: () -> Unit,
) {
    val text = rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                shape = RoundedCornerShape(
                    percent = 20
                ),
                width = 1.dp,
                color = Color.Blue
            )
            .background(Color.White),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
            onSearchTextChanged(text.value)
        },
        textStyle = MaterialTheme.typography.bodyLarge,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_hint),
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        trailingIcon = {
            if (text.value.isNotBlank()) {
                IconButton(
                    onClick = {
                        text.value = ""
                        onSearchClearClicked()
                    },
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = stringResource(id = R.string.content_description_clear_search),
                    )
                }
            }
        }
    )
}

@Composable
fun SearchResultList(
    searchResults: List<City>,
    onSearchResultClicked: (City) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(searchResults.size) { index ->
            val cityResult = searchResults[index]
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = dimensionResource(id = R.dimen.large_padding).value.dp,
                        start = dimensionResource(id = R.dimen.medium_padding).value.dp,
                        end = dimensionResource(id = R.dimen.medium_padding).value.dp
                    )
                    .clickable {
                        onSearchResultClicked(cityResult)
                    },
            ) {
                Text(
                    text = cityResult.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = " - ${cityResult.region}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                    )
                )
            }

        }
    }
}

@Preview(heightDp = 100)
@Composable
fun SearchInputSectionPreview() {
    SearchInputSection(
        modifier = Modifier,
        onSearchTextChanged = {},
        onExitSearchClicked = {},
        onSearchResultClicked = {},
        searchResults = listOf(
            City("Munich", "Bavaria"),
        ),
        onSearchClearClicked = {}
    )
}

@Composable
fun DateAndSearchIconSection(
    modifier: Modifier,
    time: String,
    onSearchClicked: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (searchIcon, timeNow) = createRefs()

        val searchIconEndGuideline = createGuidelineFromEnd(0.1f)

        IconButton(
            modifier = Modifier
                .size(24.dp)
                .constrainAs(searchIcon) {
                    end.linkTo(searchIconEndGuideline)
                    centerVerticallyTo(parent)
                },
            onClick = onSearchClicked
        ) {
            Icon(
                Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.content_description_search),
                tint = Color.White
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(timeNow) {
                    centerHorizontallyTo(parent)
                    centerVerticallyTo(parent)
                },
            text = time,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview
@Composable
fun DateAndSearchIconSectionPreview() {
    DateAndSearchIconSection(
        modifier = Modifier,
        onSearchClicked = {},
        time = "12:44"
    )
}