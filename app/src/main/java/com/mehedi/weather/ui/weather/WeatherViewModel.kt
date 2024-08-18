package com.mehedi.weather.ui.weather

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.weather.WeatherApplication
import com.mehedi.weather.data.repository.WeatherRepository
import com.mehedi.weather.utils.DEFAULT_WEATHER_DESTINATION
import com.mehedi.weather.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val application: WeatherApplication? = null,
) : ViewModel() {

    private val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState(isLoading = true))
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    init {
        getWeather()
    }

    fun getWeather(city: String = getLastLocation() ?: DEFAULT_WEATHER_DESTINATION) {
        saveLastLocation(city)
        repository.getWeatherForecast(city).map { result ->
            when (result) {
                is Result.Success -> {
                    _uiState.value = WeatherUiState(weather = result.data)
                }

                is Result.Error -> {
                    _uiState.value = WeatherUiState(errorMessage = result.errorMessage)
                }

                Result.Loading -> {
                    _uiState.value = WeatherUiState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getLastLocation(): String? {
        val sharedPreference = application?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        return sharedPreference?.getString("lastLocation", null)
    }

    private fun saveLastLocation(city: String) {
        val sharedPreference = application?.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference?.edit()?.putString("lastLocation", city)?.apply()
    }

}