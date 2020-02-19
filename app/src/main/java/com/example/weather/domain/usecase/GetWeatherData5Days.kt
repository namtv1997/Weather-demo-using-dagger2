package com.example.weather.domain.usecase

import com.example.weather.domain.remote.pojo.response.WeatherResult
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherData5Days @Inject constructor(private val repository: WeatherRepository) :
    SingleUseCase<WeatherResult>() {

    private var keyRegion: String? = null
    private var apiKey: String? = null

    fun saveKeyRegionAndApiKey(regionKey: String,key:String) {
        keyRegion = regionKey
        apiKey=key
    }

    override fun buildUseCaseSingle(): Single<WeatherResult> {
        return repository.getWeatherData5Days(keyRegion,apiKey,true)
    }
}