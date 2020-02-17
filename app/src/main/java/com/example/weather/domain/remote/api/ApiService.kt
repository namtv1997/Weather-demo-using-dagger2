package com.example.weather.domain.remote.api

import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("locations/v1/cities/geoposition/search")
    fun getWeatherDataByGeoPositionSearch(
        @Query("apikey") apikey: String,
        @Query("q") q: String
    ): Observable<GeoPositionSearch>
}