package com.example.wethertestingapp

sealed class Screens(val route: String) {
    object CurrentCityWether: Screens("CurrentCityWether")
    object WeatherForecastScreen: Screens("WeatherForecastScreen")
}