package com.example.weather.domain.usecase

import com.example.weather.data.response.GeoPositionSearch
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetWeatherDataByGeoPositionSearchUseCase @Inject constructor(private val repository: WeatherRepository) :
    ObservableUseCase<GeoPositionSearch>() {

    private var q: String? = null

    fun saveLatAndLon(latitue:String) {
        q=latitue
    }

    override fun buildUseCaseSingle(): Observable<GeoPositionSearch> {
        return repository.getWeatherDataByGeoPositionSearch(q)
    }
}