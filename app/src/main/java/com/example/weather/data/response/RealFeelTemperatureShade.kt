package com.example.weather.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RealFeelTemperatureShade (
    @SerializedName("Minimum")
    @Expose
    val minimum: Minimum = Minimum(),

    @SerializedName("Maximum")
    @Expose
    val maximum: Maximum = Maximum()
)