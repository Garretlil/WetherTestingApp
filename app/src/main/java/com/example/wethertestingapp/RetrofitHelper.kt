package com.example.wethertestingapp

import com.example.wethertestingapp.wetherData.WeatherForecastResponse
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain?,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class Coord(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class Rain(
    val h1: Double
)

data class Clouds(
    val all: Int
)

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)


interface QuotesApi {

    @GET("weather?")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String,
        @Query("lang") lang: String = "ru"
    ): Response<WeatherResponse>

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city: String,
        @Query("appid") appid: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ru"
    ): Response<WeatherForecastResponse>

}

class RetrofitHelper {

    private val baseUrl = "http://api.openweathermap.org/data/2.5/"
    private val quotesApi: QuotesApi = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuotesApi::class.java)

       fun getWeatherByCity(city: String): WeatherResponse {
         var wether:WeatherResponse
         runBlocking {
             wether = quotesApi.getWeather(city, appid = "e05d57260003a4d3a7df806ae18d86ed").body()!!
          }
         return wether
       }
        fun getWeatherForecastByCity(city: String): WeatherForecastResponse {
            var wether:WeatherForecastResponse
            runBlocking {
                wether = quotesApi.getForecast(city, appid = "e05d57260003a4d3a7df806ae18d86ed").body()!!
            }
            return wether
        }
}
