package com.mehedi.weather.ui.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehedi.weather.R
import com.mehedi.weather.model.Forecast
import com.mehedi.weather.ui.weather.components.ForecastComponent

@Composable
fun FutureWeatherScreen(
    data: List<Forecast>
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.forecast), fontSize = 24.sp,  modifier = Modifier
            .padding(24.dp))
    }

    Spacer(Modifier.height(50.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 80.dp, start = 16.dp),
    ) {
        items(data) { forecast ->
            ForecastComponent(
                date = forecast.date,
                icon = forecast.icon,
                conditionText = forecast.conditionText,
                minTemp = stringResource(
                    R.string.temperature_value_in_celsius,
                    forecast.minTemp
                ),
                maxTemp = stringResource(
                    R.string.temperature_value_in_celsius,
                    forecast.maxTemp,
                ),
                feelsLikeTemp = stringResource(
                    R.string.temperature_value_in_celsius,
                    forecast.hour.firstOrNull()?.feelsLike ?: ""
                )
            )
        }
    }
    Spacer(Modifier.height(16.dp))
}
