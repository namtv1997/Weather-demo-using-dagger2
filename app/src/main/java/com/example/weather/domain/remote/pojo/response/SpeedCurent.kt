package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SpeedCurent (
    @SerializedName("Metric")
    @Expose
    val metric: SpeedDouble = SpeedDouble(),
    @SerializedName("Imperial")
    @Expose
    val imperial: SpeedDouble = SpeedDouble()
)