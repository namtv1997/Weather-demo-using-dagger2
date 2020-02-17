package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("ID")
    @Expose
    val iD: String? = null,
    @SerializedName("LocalizedName")
    @Expose
    val localizedName: String? = null,
    @SerializedName("EnglishName")
    @Expose
    val englishName: String? = null
)