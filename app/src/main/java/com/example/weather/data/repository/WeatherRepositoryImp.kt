package com.example.weather.data.repository

import com.example.weather.data.source.remote.ApiService
import com.example.weather.data.response.GeoPositionSearch
import com.example.weather.data.response.WeatherCurent
import com.example.weather.data.response.WeatherResult
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.presentation.base.BaseActivity.Companion.apiKey
import io.reactivex.Observable

class WeatherRepositoryImp( private  val apiService: ApiService) :WeatherRepository{

    override fun getWeatherDataByGeoPositionSearch(q: String?): Observable<GeoPositionSearch> {
        return apiService.getWeatherDataByGeoPositionSearch(apiKey,q.toString())
    }

    override fun getWeatherData5Days(keyRegion: String?, details: Boolean): Observable<WeatherResult> {
        return apiService.getWeatherData5Days(keyRegion.toString(),apiKey,true)
    }

    override fun getWeatherDataCurrent(keyRegion: String?, details: Boolean): Observable<ArrayList<WeatherCurent>> {
        return apiService.getWeatherDataCurrent(keyRegion.toString(),apiKey,true)
    }
}