package com.example.weather.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.domain.remote.pojo.response.WeatherResult
import com.example.weather.domain.usecase.GetWeatherData5Days
import javax.inject.Inject

class Weather5daysViewModel @Inject constructor(private val getWeatherData5Days: GetWeatherData5Days) :
    ViewModel() {

    val weather5days = MutableLiveData<WeatherResult>()

    val isLoad = MutableLiveData<Boolean>()

    fun getDataWeather5days(keyRegion: String, apikey: String) {
        isLoad.value = true
        getWeatherData5Days.saveKeyRegionAndApiKey(keyRegion, apikey)
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