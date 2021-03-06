package com.example.weather.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RealFeelTemperatureShadeCurrent (
    @SerializedName("Metric")
    @Expose
    val metric: SpeedCurent = SpeedCurent(),
    @SerializedName("Imperial")
    @Expose
    val imperial: SpeedCurent = SpeedCurent()
)