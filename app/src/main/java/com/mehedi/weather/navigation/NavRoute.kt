package com.mehedi.weather.navigation

sealed class NavRoute(val path: String) {

    object CurrentWeather: NavRoute("currentWeather")

    object FutureWeather: NavRoute("futureWeather")
}


