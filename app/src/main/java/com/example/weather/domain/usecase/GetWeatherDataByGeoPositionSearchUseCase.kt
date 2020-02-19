package com.example.weather.domain.usecase

import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherDataByGeoPositionSearchUseCase @Inject constructor(private val repository: WeatherRepository) :
    SingleUseCase<GeoPositionSearch>() {

    private var apiKey: String? = null
    private var q: String? = null

    fun saveApiKeyAndQ(key: String,latitue:String) {
        apiKey = key
        q=latitue
    }

    override fun buildUseCaseSingle(): Single<GeoPositionSearch> {
        return repository.getWeatherDataByGeoPositionSearch(apiKey,q)
    }
}