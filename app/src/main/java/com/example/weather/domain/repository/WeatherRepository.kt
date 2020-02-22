package com.example.weather.domain.repository

import com.example.weather.data.response.GeoPositionSearch
import com.example.weather.data.response.WeatherCurent
import com.example.weather.data.response.WeatherResult
import io.reactivex.Observable

interface WeatherRepository {

    fun getWeatherDataByGeoPositionSearch(q: String?) :Observable<GeoPositionSearch>

    fun getWeatherData5Days(keyRegion: String?,details: Boolean) : Observable<WeatherResult>

    fun getWeatherDataCurrent(keyRegion: String?,details: Boolean) :Observable<ArrayList<WeatherCurent>>

}