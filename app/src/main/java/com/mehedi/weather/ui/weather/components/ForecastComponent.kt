package com.mehedi.weather.ui.weather.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mehedi.weather.R
import com.mehedi.weather.ui.theme.WeatherTheme
import com.mehedi.weather.utils.DateUtil.toFormattedDay

@Composable
fun ForecastComponent(
    modifier: Modifier = Modifier,
    date: String,
    icon: String,
    conditionText: String,
    minTemp: String,
    maxTemp: String,
    feelsLikeTemp: String
) {
    ElevatedCard(
        modifier = modifier.padding(end = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .width(130.dp)
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = date.toFormattedDay().orEmpty(),
                style = MaterialTheme.typography.titleMedium
            )
            AsyncImage(
                modifier = Modifier.size(42.dp),
                model = stringResource(R.string.icon_image_url, icon),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_placeholder),
                placeholder = painterResource(id = R.drawable.ic_placeholder),
            )
            Text(
                text = conditionText,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = maxTemp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = minTemp, style = MaterialTheme.typography.bodySmall
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = "Feels like $feelsLikeTemp at 12 AM", style = MaterialTheme.typography.bodySmall, fontSize = 8.sp
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ForecastComponentPreview() {
    Surface {
        WeatherTheme {
            ForecastComponent(
                date = "2023-10-07",
                icon = "116.png",
                minTemp = "12",
                maxTemp = "28",
                conditionText = "cloudy",
                feelsLikeTemp = "23"
            )
        }
    }
}