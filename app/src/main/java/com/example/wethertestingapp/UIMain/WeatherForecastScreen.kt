package com.example.wethertestingapp.UIMain


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wethertestingapp.Screens
import com.example.wethertestingapp.WeatherResponse
import com.example.wethertestingapp.WhetherViewModel
import com.example.wethertestingapp.ui.theme.ColorForMainCard
import com.example.wethertestingapp.wetherData.WeatherForecastItem

@Composable
fun WeatherForecastScreen(
    viewModel: WhetherViewModel,
    NavController: NavHostController
) {
    val context = LocalContext.current
    try {
        viewModel.getWeatherForecast(viewModel.currentCity)

    } catch (e: Exception) {
        when (e) {
            is NullPointerException -> {
                Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Сервер не отвечает")
                }
            }
            is IndexOutOfBoundsException -> {
                Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Сервер не отвечает")
                }
            }
            else -> {
                Toast.makeText(context, "Сервер в технических работах", Toast.LENGTH_SHORT).show()
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Сервер не отвечает")
                }
            }
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        //.padding(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = ColorForMainCard
        ),
        //elevation = 0.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Button(
                modifier = Modifier.padding(30.dp),
                onClick = {
                    NavController.navigate(Screens.CurrentCityWether.route)
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Поиск",
                    tint = Color.Black
                )
                Text("Вернуться к выбору города", color = Color.Black)

            }

            Text(
                text = "Прогноз погоды",
                //style = MaterialTheme.typography.h5
            )
            // Прогноз на каждый день
            LazyVerticalGrid(
                modifier = Modifier.padding(18.dp),
                columns = GridCells.Fixed(1),
                //verticalArrangement = Arrangement.spacedBy(8.dp),
                //horizontalArrangement = Arrangement.spacedBy(8.dp),
                //contentPadding = PaddingValues(8.dp)
            ) {
                items(viewModel.gettedWeatherForecast.list) { forecastDay ->
                    WeatherForecastItem(forecastDay, viewModel)
                }
            }
        }
    }
}

@Composable
fun WeatherForecastItem(forecastDay: WeatherForecastItem, viewModel: WhetherViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp),

        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // День недели
            Text(
                text = forecastDay.dtTxt,
                //style = MaterialTheme.typography.
            )
            // Иконка погоды
            Icon(
                imageVector = Icons.Default.Star, // Замените на подходящую иконку
                contentDescription = forecastDay.weather[0].description,
                tint = Color.Gray
            )
            // Температура
            Text(
                text = "${forecastDay.main.temp}°C",
                //style = MaterialTheme.typography.h6
            )
            // Влажность
            Text(
                text = "Влажность: ${forecastDay.main.humidity}%",
                style = MaterialTheme.typography.labelSmall
            )
            // Скорость ветра
            Text(
                text = "Ветер: ${forecastDay.wind.speed} м/с",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}