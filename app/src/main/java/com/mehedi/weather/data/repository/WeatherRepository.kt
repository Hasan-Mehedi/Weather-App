package com.mehedi.weather.data.repository

import com.mehedi.weather.model.Weather
import com.mehedi.weather.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeatherForecast(city: String): Flow<Result<Weather>>
}