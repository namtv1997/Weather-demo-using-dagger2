package com.example.weather.domain.remote.pojo.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TimeZone(
    @SerializedName("Code")
    @Expose
    val code: String? = null,
    @SerializedName("Name")
    @Expose
    val name: String? = null,
    @SerializedName("GmtOffset")
    @Expose
    val gmtOffset: Int? = null,
    @SerializedName("IsDaylightSaving")
    @Expose
    val isDaylightSaving: Boolean? = null,
    @SerializedName("NextOffsetChange")
    @Expose
    val nextOffsetChange: Any? = null
)