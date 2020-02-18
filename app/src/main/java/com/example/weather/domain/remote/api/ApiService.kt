package com.example.weather.domain.remote.api

import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.remote.pojo.response.WeatherResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("locations/v1/cities/geoposition/search")
    fun getWeatherDataByGeoPositionSearch(
        @Query("apikey") apikey: String,
        @Query("q") q: String
    ): Observable<GeoPositionSearch>

    @GET("forecasts/v1/daily/5day/{keyRegion}")
    fun getWeatherData5Days(
        @Path("keyRegion") keyRegion: String,
        @Query("apikey") apikey: String,
        @Query("details") details: Boolean
    ): Observable<WeatherResult>

    @GET("/currentconditions/v1/{keyRegion}")
    fun getWeatherDataCurrent(
        @Path("keyRegion") keyRegion: String,
        @Query("apikey") apikey: String,
        @Query("details") details: Boolean
    ): Observable<List<WeatherCurent>>
}