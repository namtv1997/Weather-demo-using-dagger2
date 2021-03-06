package com.example.weather.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data class Elevation(
    @SerializedName("Metric")
    @Expose
    val metric: Metric? = null,
    @SerializedName("Imperial")
    @Expose
    val imperial: Imperial? = null
)