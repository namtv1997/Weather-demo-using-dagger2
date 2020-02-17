package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WindGustCurrent(
    @SerializedName("Speed")
    @Expose
    val speed: SpeedCurent = SpeedCurent()
)