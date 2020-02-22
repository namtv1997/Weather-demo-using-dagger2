package com.example.weather.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("ID")
    @Expose
    val iD: String? = null,
    @SerializedName("LocalizedName")
    @Expose
    val localizedName: String? = null,
    @SerializedName("EnglishName")
    @Expose
    var englishName: String? = null
)