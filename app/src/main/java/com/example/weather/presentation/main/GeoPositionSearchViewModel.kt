package com.example.weather.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.usecase.GetWeatherDataByGeoPositionSearchUseCase
import javax.inject.Inject

class GeoPositionSearchViewModel @Inject constructor(private  val getWeatherDataByGeoPositionSearchUseCase: GetWeatherDataByGeoPositionSearchUseCase) : ViewModel(){

    val resultGeoPositionSearch = MutableLiveData<GeoPositionSearch>()
    val isLoad = MutableLiveData<Boolean>()

    init {
        isLoad.value = true
    }

    fun getDataGeoPositionSearch(apikey: String, q: String) {
        getWeatherDataByGeoPositionSearchUseCase.saveApiKeyAndQ(apikey,q)
        getWeatherDataByGeoPositionSearchUseCase.execute(
            onSuccess =  {
                isLoad.value = false
                resultGeoPositionSearch.postValue(it)
            },
            onError = {
                isLoad.value = false
                it.printStackTrace()
            }
        )

    }

}