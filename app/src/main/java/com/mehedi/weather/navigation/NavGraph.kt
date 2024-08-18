package com.mehedi.weather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mehedi.weather.model.Forecast
import com.mehedi.weather.ui.weather.FutureWeatherScreen
import com.mehedi.weather.ui.weather.WeatherScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoute.CurrentWeather.path
    ) {
        addCurrentWeatherScreen(navController, this)

        addFutureWeatherScreen(navController, this)
    }
}

private fun addCurrentWeatherScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.CurrentWeather.path) {
        WeatherScreen(
            navHostController = navController,
            navigateToFutureWeatherScreen = {
                navController.navigate(NavRoute.FutureWeather.path)
            }
        )
    }
}

private fun addFutureWeatherScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder
) {
    navGraphBuilder.composable(route = NavRoute.FutureWeather.path) {
        val data =
            navController.previousBackStackEntry?.savedStateHandle?.get<List<Forecast>>("data")
                ?: listOf()
        FutureWeatherScreen(data)
    }
}
