package com.example.wethertestingapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wethertestingapp.UIMain.MainCardWether
import com.example.wethertestingapp.UIMain.WeatherForecastScreen

@Composable
fun NavGraph (viewModel: WhetherViewModel, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.CurrentCityWether.route)
    {
        composable(route = Screens.CurrentCityWether.route) {
            MainCardWether(viewModel, navController)
        }
        composable(route = Screens.WeatherForecastScreen.route) {
            WeatherForecastScreen(viewModel, navController)
        }
    }
}