package com.example.weather.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weather.domain.remote.api.ApiService
import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.remote.pojo.response.WeatherResult
import com.example.weather.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

 class MainViewModel @Inject constructor(
    apiService: ApiService
) : BaseViewModel() {
    private val apiService: ApiService =apiService

     val weather5days = MutableLiveData<WeatherResult>()
     val resultGeoPositionSearch = MutableLiveData<GeoPositionSearch>()
     val weatherCurrent = MutableLiveData<List<WeatherCurent>>()

    fun getDataGeoPositionSearch(apikey: String, q: String) {
        addDisposable(
            apiService.getWeatherDataByGeoPositionSearch(apikey, q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingStatus.postValue(true) }
                .doOnNext { loadingStatus.postValue(false) }
                .doOnError { loadingStatus.postValue(false) }
                .subscribe({
                    resultGeoPositionSearch.postValue(it)
                }, {
                    Log.d("ff", it.message.toString())
                })
        )
    }

     fun getDataWeatherCurrent(keyRegion: String, apikey: String, details: Boolean) {
         addDisposable(
             apiService.getWeatherDataCurrent(keyRegion, apikey, details)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .doOnSubscribe { loadingStatus.postValue(true) }
                 .doOnNext { loadingStatus.postValue(false) }
                 .doOnError { loadingStatus.postValue(false) }
                 .subscribe({
                     weatherCurrent.postValue(it)
                 }, {
                     Log.d("ff", it.message.toString())
                 })
         )
     }

     fun getDataWeather5days(keyRegion: String, apikey: String, details: Boolean) {
         addDisposable(
             apiService.getWeatherData5Days(keyRegion, apikey, details)
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .doOnSubscribe { loadingStatus.postValue(true) }
                 .doOnNext { loadingStatus.postValue(false) }
                 .doOnError { loadingStatus.postValue(false) }
                 .subscribe({
                     Log.d("ff","run hear 5 day")
                     weather5days.postValue(it)
                 }, {
                     Log.d("ff",it.message.toString())
                 })
         )
     }
}