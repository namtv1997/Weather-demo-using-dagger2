package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RealFeelTemperature(
    @SerializedName("Minimum")
    @Expose
    val minimum: Minimum = Minimum(),

    @SerializedName("Maximum")
    @Expose
    val maximum: Maximum = Maximum()
)