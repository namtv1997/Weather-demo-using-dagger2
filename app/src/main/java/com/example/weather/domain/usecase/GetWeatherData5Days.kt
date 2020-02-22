package com.example.weather.domain.usecase

import com.example.weather.data.response.WeatherResult
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetWeatherData5Days @Inject constructor(private val repository: WeatherRepository) :
    ObservableUseCase<WeatherResult>() {

    private var keyRegion: String? = null

    fun saveKeyRegion(regionKey: String) {
        keyRegion = regionKey
    }

    override fun buildUseCaseSingle(): Observable<WeatherResult> {
        return repository.getWeatherData5Days(keyRegion,true)
    }
}