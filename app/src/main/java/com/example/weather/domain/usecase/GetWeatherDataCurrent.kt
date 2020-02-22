package com.example.weather.domain.usecase

import com.example.weather.data.response.WeatherCurent
import com.example.weather.domain.repository.WeatherRepository
import com.example.weather.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetWeatherDataCurrent @Inject constructor(private val repository: WeatherRepository) :
    ObservableUseCase<ArrayList<WeatherCurent>>() {

    private var keyRegion: String? = null

    fun saveLatAndLon(latitue:String) {
        keyRegion=latitue
    }

    override fun buildUseCaseSingle(): Observable<ArrayList<WeatherCurent>> {
        return repository.getWeatherDataCurrent(keyRegion,true)
    }
}