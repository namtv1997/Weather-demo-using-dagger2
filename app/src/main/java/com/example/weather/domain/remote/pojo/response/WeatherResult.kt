package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data class WeatherResult(


    @SerializedName("Headline")
    @Expose
    val Headline: Headline = Headline(),

    @SerializedName("DailyForecasts")
    @Expose
    val DailyForecasts: List<DailyForecast>? = null

)