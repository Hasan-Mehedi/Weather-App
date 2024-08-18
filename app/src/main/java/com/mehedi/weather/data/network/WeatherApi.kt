package com.mehedi.weather.data.network

import com.mehedi.weather.BuildConfig
import com.mehedi.weather.data.model.ForecastResponse
import com.mehedi.weather.utils.DEFAULT_WEATHER_DESTINATION
import com.mehedi.weather.utils.NUMBER_OF_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): ForecastResponse
}