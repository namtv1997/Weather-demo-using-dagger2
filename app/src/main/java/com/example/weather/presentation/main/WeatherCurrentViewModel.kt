package com.example.weather.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.usecase.GetWeatherDataCurrent
import javax.inject.Inject

class WeatherCurrentViewModel @Inject constructor(private  val getWeatherDataCurrent: GetWeatherDataCurrent) : ViewModel(){

    val weatherCurrent = MutableLiveData<List<WeatherCurent>>()

    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = true
    }


    fun getDataWeatherCurrent(keyRegion: String, apikey: String) {
        getWeatherDataCurrent.saveKeyRegionAndApiKey(keyRegion,apikey)
        getWeatherDataCurrent.execute(
            onSuccess =   {
                isLoad.value = false
                weatherCurrent.postValue(it)
            },
            onError =       {
                isLoad.value = false
                it.printStackTrace()
            }
        )

    }
}