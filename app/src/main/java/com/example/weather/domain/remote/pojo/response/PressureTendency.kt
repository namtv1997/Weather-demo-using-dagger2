package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PressureTendency(
    @SerializedName("LocalizedText")
    @Expose
    val localizedText: String? = null,

    @SerializedName("Code")
    @Expose
    val code: String? = null


)