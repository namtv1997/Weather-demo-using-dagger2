package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sun(
    @SerializedName("Rise")
    @Expose
    val rise: String? = null,

    @SerializedName("EpochRise")
    @Expose
    val epochRise: Int? = null,

    @SerializedName("Set")
    @Expose
    val set: String? = null,

    @SerializedName("EpochSet")
    @Expose
    val epochSet: Int? = null
)