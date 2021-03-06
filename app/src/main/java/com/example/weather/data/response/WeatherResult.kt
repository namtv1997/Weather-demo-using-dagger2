package com.example.weather.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data class WeatherResult(


    @SerializedName("Headline")
    @Expose
    var Headline: Headline = Headline(),

    @SerializedName("DailyForecasts")
    @Expose
    var DailyForecasts: List<DailyForecast>? = null

)