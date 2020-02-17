package com.example.weather.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.weather.domain.remote.api.ApiService
import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

 class MainViewModel @Inject constructor(
    apiService: ApiService
) : BaseViewModel() {
    val apiService: ApiService =apiService

    val resultGeoPositionSearch = MutableLiveData<GeoPositionSearch>()

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
}