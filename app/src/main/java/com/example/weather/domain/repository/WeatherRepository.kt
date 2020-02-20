package com.example.weather.domain.repository

import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.remote.pojo.response.WeatherResult
import io.reactivex.Single

interface WeatherRepository {
    fun getWeatherDataByGeoPositionSearch(apikey: String?,q: String?) :Single<GeoPositionSearch>

    fun getWeatherData5Days(keyRegion: String?,apikey: String?,details: Boolean) :Single<WeatherResult>

    fun getWeatherDataCurrent(keyRegion: String?,apikey: String?,details: Boolean) :Single<ArrayList<WeatherCurent>>

}