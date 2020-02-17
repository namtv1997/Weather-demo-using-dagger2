package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TemperatureSummary(
    @SerializedName("Past6HourRange")
    @Expose
    val past6HourRange: Past6HourRange = Past6HourRange(),

    @SerializedName("Past12HourRange")
    @Expose
    val past12HourRange: Past12HourRange = Past12HourRange(),

    @SerializedName("Past24HourRange")
    @Expose
    val past24HourRange: Past24HourRange = Past24HourRange()
)