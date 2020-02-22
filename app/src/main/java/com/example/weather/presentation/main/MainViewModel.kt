package com.example.weather.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.response.GeoPositionSearch
import com.example.weather.data.response.WeatherCurent
import com.example.weather.data.response.WeatherResult
import com.example.weather.domain.usecase.GetWeatherData5Days
import com.example.weather.domain.usecase.GetWeatherDataByGeoPositionSearchUseCase
import com.example.weather.domain.usecase.GetWeatherDataCurrent
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getWeatherDataByGeoPositionSearchUseCase: GetWeatherDataByGeoPositionSearchUseCase,
    private val getWeatherDataCurrent: GetWeatherDataCurrent,
    private val getWeatherData5Days: GetWeatherData5Days) : ViewModel() {

    var resultGeoPositionSearch = MutableLiveData<GeoPositionSearch>()
    var weather5days = MutableLiveData<WeatherResult>()
    var weatherCurrent = MutableLiveData<ArrayList<WeatherCurent>>()
    val isLoad = MutableLiveData<Boolean>()

    fun getDataGeoPositionSearch(q: String) {
        isLoad.value = true
        getWeatherDataByGeoPositionSearchUseCase.saveLatAndLon(q)
        getWeatherDataByGeoPositionSearchUseCase.execute(
            onSuccess = {
                isLoad.value = false
                resultGeoPositionSearch.postValue(it)
            },
            onError = {
                isLoad.value = false
                it.printStackTrace()
            }
        )

    }

    fun getDataWeatherCurrent(keyRegion: String) {
        isLoad.value = true
        getWeatherDataCurrent.saveLatAndLon(keyRegion)
        getWeatherDataCurrent.execute(
            onSuccess = {
                isLoad.value = false
                weatherCurrent.postValue(it)
            },
            onError = {
                isLoad.value = false
                it.printStackTrace()
            }
        )

    }

    fun getDataWeather5days(keyRegion: String) {
        isLoad.value = true
        getWeatherData5Days.saveKeyRegion(keyRegion)
        getWeatherData5Days.execute(
            onSuccess = {
                isLoad.value = false
                weather5days.postValue(it)
            },
            onError = {
                isLoad.value = false
                it.printStackTrace()
            }
        )

    }

}