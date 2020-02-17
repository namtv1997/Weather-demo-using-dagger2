package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PastHour (
    @SerializedName("Metric")
    @Expose
    val metric: SpeedCurent = SpeedCurent(),
    @SerializedName("Imperial")
    @Expose
    val imperial: SpeedCurent = SpeedCurent()
)