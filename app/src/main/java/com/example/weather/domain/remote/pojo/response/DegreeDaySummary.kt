package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DegreeDaySummary(

    @SerializedName("Heating")
    @Expose
    val heating: Heating = Heating(),

    @SerializedName("Cooling")
    @Expose
    val cooling:Cooling=Cooling()
)