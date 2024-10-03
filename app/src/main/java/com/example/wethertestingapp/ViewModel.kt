package com.example.wethertestingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.wethertestingapp.wetherData.WeatherForecastResponse


class WhetherViewModel (application: Application ): AndroidViewModel(application) {

    val remoteDB:RetrofitHelper=RetrofitHelper()
    lateinit var gettedweather: WeatherResponse
    lateinit var gettedWeatherForecast: WeatherForecastResponse
    var currentCity="Moscow"

     fun getWether(city:String){
        this.gettedweather=remoteDB.getWeatherByCity(city)
    }
    fun getWeatherForecast(city:String){
        this.gettedWeatherForecast=remoteDB.getWeatherForecastByCity(city)
    }

}