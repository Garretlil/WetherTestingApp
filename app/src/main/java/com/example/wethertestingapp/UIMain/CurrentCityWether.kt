package com.example.wethertestingapp.UIMain

import android.icu.text.SimpleDateFormat
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.wethertestingapp.WhetherViewModel
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.example.wethertestingapp.R
import com.example.wethertestingapp.Screens
import com.example.wethertestingapp.WeatherResponse
import com.example.wethertestingapp.ui.theme.ColorForMainCard
import java.util.Date
import kotlin.math.round


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCardWether(viewModel: WhetherViewModel,NavController: NavHostController) {
    var query by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    val currentDateAndTime = sdf.format(Date())

    var city by remember { mutableStateOf("") }
    var temp by remember { mutableDoubleStateOf(0.0) }
    var description by remember { mutableStateOf("") }

    try {
        viewModel.getWether(viewModel.currentCity)
        viewModel.getWeatherForecast(viewModel.currentCity)

        city = viewModel.gettedweather.name
        temp = viewModel.gettedweather.main.temp
        description = viewModel.gettedweather.weather[0].description
    } catch (e: Exception) {
        when (e) {
            is NullPointerException -> {
                Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
            }
            is IndexOutOfBoundsException -> {
                Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Сервер в технических работах", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val j="K"
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(5.dp),

        ) {

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
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Text(
                            modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                            text = currentDateAndTime,
                            style = androidx.compose.ui.text.TextStyle(fontSize = 15.sp),
                            color = Color.Black
                        )
                        AsyncImage(
                            model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                            contentDescription = "im2",
                            modifier = Modifier
                                .size(35.dp)
                                .padding(top = 3.dp, end = 8.dp)
                        )
                    }
                    Text(
                        text = city,
                        style = TextStyle(fontSize = 24.sp),
                        color = Color.White
                    )
                    Text(
                        text = round(temp).toInt().toString() + "° C",
                        style = TextStyle(fontSize = 65.sp),
                        color = Color.White
                    )
                    Text(
                        text = description,
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {

                    }
                    var name by remember { mutableStateOf("") }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Введите город") },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.Black, // Цвет границы в неактивном состоянии
                                focusedBorderColor = Color.Black // Цвет границы в активном состоянии
                            ),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {
                            try {

                                viewModel.getWether(name)
                                viewModel.getWeatherForecast(name)
                                city = viewModel.gettedweather.name
                                temp = viewModel.gettedweather.main.temp
                                description = viewModel.gettedweather.weather[0].description
                                viewModel.currentCity=name
                            } catch (e: Exception) {
                                when (e) {
                                    is NullPointerException -> {
                                        Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
                                    }
                                    is IndexOutOfBoundsException -> {
                                        Toast.makeText(context, "Ошибка получения данных", Toast.LENGTH_SHORT).show()
                                    }
                                    else -> {
                                        Toast.makeText(context, "Сервер в технических работах", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Поиск",
                                tint = Color.Black
                            )
                        }
                    }
                    Button(
                        modifier = Modifier.padding(30.dp),
                        onClick = {
                            NavController.navigate(Screens.WeatherForecastScreen.route)
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(Color.Black)
                    ) {
                        Text("Более подробный прогноз", color = Color.White)
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Поиск",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}
