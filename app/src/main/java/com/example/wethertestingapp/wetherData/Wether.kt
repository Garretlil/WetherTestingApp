package com.example.wethertestingapp.wetherData

import com.google.gson.annotations.SerializedName

data class WeatherForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherForecastItem>,
    val city: City
)

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Long,
    val sunset: Long
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class WeatherForecastItem(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    @SerializedName("dt_txt") val dtTxt: String,
    val visibility: Int,
    val pop: Double,
    val rain: Rain?,
    val sys: Sys
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Clouds(
    val all: Int
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class Rain(
    @SerializedName("1h") val oneHour: Double
)

data class Sys(
    val pod: String
)