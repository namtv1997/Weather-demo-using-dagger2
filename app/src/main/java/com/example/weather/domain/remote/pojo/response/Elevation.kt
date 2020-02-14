package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Elevation {
    @SerializedName("Metric")
    @Expose
    val metric: Metric? = null
    @SerializedName("Imperial")
    @Expose
    val imperial: Imperial? = null
}