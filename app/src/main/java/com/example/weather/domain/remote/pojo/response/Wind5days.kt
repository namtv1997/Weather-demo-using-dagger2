package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data class Wind5days (
    @SerializedName("Speed")
    @Expose
    val speed: SpeedDouble = SpeedDouble(),

    @SerializedName("Direction")
    @Expose
    val direction: Direction = Direction()
)