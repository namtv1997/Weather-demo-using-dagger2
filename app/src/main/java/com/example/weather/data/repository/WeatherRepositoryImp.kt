package com.example.weather.data.repository

import com.example.weather.data.source.remote.ApiService
import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.remote.pojo.response.WeatherResult
import com.example.weather.domain.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImp( private  val apiService: ApiService) :WeatherRepository{

    override fun getWeatherDataByGeoPositionSearch(
        apikey: String?,
        q: String?
    ): Single<GeoPositionSearch> {
        return apiService.getWeatherDataByGeoPositionSearch(apikey.toString(),q.toString())
    }

    override fun getWeatherData5Days(
        keyRegion: String?,
        apikey: String?,
        details: Boolean
    ): Single<WeatherResult> {
        return apiService.getWeatherData5Days(keyRegion.toString(),apikey.toString(),true)
    }

    override fun getWeatherDataCurrent(
        keyRegion: String?,
        apikey: String?,
        details: Boolean
    ): Single<ArrayList<WeatherCurent>> {
        return apiService.getWeatherDataCurrent(keyRegion.toString(),apikey.toString(),true)
    }
}