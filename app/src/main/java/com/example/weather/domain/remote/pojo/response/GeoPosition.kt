package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GeoPosition {
    @SerializedName("Latitude")
    @Expose
     val latitude: Double? = null
    @SerializedName("Longitude")
    @Expose
     val longitude: Double? = null
    @SerializedName("Elevation")
    @Expose
     val elevation: Elevation? = null
}