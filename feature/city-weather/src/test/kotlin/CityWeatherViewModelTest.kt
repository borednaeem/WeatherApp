import app.cash.turbine.test
import com.example.weatherapp.core.domain.usecase.getweather.IGetWeatherUseCase
import com.example.weatherapp.core.domain.usecase.searchcity.ISearchCityUseCase
import com.example.weatherapp.core.domain_data.Constants
import com.example.weatherapp.core.domain_data.model.CityWeather
import com.example.weatherapp.core.domain_data.model.DayWeatherSummary
import com.example.weatherapp.feature.city_weather.CityWeatherScreenState
import com.example.weatherapp.feature.city_weather.CityWeatherViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class CityWeatherViewModelTest {

    private lateinit var getWeather: IGetWeatherUseCase
    private lateinit var searchCity: ISearchCityUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // mock dependencies
        getWeather = mockk<IGetWeatherUseCase>()
        searchCity = mockk<ISearchCityUseCase>()
    }

    @Test
    fun loadWeatherData_whenLoadingCityWeatherStarts_showLoading() = runTest {
        // stub weather use case
        coEvery { getWeather.invoke(any()) } coAnswers {
            // simulate network operation delay
            delay(1000L)

            testCityWeather
        }

        // init CityWeatherViewModel instance
        val viewModel = CityWeatherViewModel(searchCity, getWeather)

        // load weather data with city name
        viewModel.loadWeatherData(testCityWeather.cityName)

        // validate emitted screen state
        viewModel.screenState.test {
            // Await loading state
            val state = awaitItem()
            assert(state is CityWeatherScreenState.Loading)
        }
    }

    @Test
    fun loadWeatherData_getsWeatherForSpecifiedCity() = runTest {
        // stub weather use case
        coEvery { getWeather.invoke(any()) } coAnswers {
            // simulate network operation delay
            delay(1000L)

            testCityWeather
        }

        // init CityWeatherViewModel instance
        val viewModel = CityWeatherViewModel(searchCity, getWeather)

        // load weather data with city name
        viewModel.loadWeatherData(testCityWeather.cityName)

        // validate emitted screen state
        viewModel.screenState.test {
            // Await loading state
            awaitItem()

            // Await success state
            val state = awaitItem()
            assertEquals(
                (state as CityWeatherScreenState.ShowData).cityWeather,
                testCityWeather
            )
        }
    }

    @Test
    fun loadWeatherData_whenLoadingCityWeatherFails_showError() = runTest {
        // stub weather use case
        coEvery { getWeather.invoke(any()) } coAnswers {
            // simulate network operation delay
            delay(1000L)

            throw RuntimeException("Test error")
        }

        // init CityWeatherViewModel instance
        val viewModel = CityWeatherViewModel(searchCity, getWeather)

        // When loading weather data for the city
        viewModel.loadWeatherData(testCityWeather.cityName, ignoreError = false)

        // validate emitted screen state
        viewModel.screenState.test {
            // Await loading state
            awaitItem()

            // Await error
            val state = awaitItem()
            assert(state is CityWeatherScreenState.Error)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

val testCityWeather = CityWeather(
    cityName = Constants.defaultCityName,
    date = "01/01/2000",
    time = "02:00",
    temperatureInFahrenheit = 70f,
    temperatureImage = "",
    weatherDescription = "Sunny all the day",
    summaryItems = listOf(
        DayWeatherSummary(
            weatherImage = "",
            highTempInFahrenheit = 80f,
            lowTempInFahrenheit = 75f,
            dayName = "Tomorrow",
            isToday = false,
            isTomorrow = true
        ),
        DayWeatherSummary(
            weatherImage = "",
            highTempInFahrenheit = 80f,
            lowTempInFahrenheit = 75f,
            dayName = "Sunday",
            isToday = false,
            isTomorrow = true
        ),
        DayWeatherSummary(
            weatherImage = "",
            highTempInFahrenheit = 80f,
            lowTempInFahrenheit = 75f,
            dayName = "Monday",
            isToday = false,
            isTomorrow = true
        ),
        DayWeatherSummary(
            weatherImage = "",
            highTempInFahrenheit = 80f,
            lowTempInFahrenheit = 75f,
            dayName = "Tuesday",
            isToday = false,
            isTomorrow = true
        ),
        DayWeatherSummary(
            weatherImage = "",
            highTempInFahrenheit = 80f,
            lowTempInFahrenheit = 75f,
            dayName = "Wednesday",
            isToday = false,
            isTomorrow = true
        ),
    )
)