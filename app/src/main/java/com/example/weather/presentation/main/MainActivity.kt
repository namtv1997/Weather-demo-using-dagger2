package com.example.weather.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.domain.remote.pojo.common.DateTimeCustom
import com.example.weather.domain.remote.pojo.common.DayCustom
import com.example.weather.domain.remote.pojo.response.GeoPositionSearch
import com.example.weather.domain.remote.pojo.response.WeatherCurent
import com.example.weather.domain.remote.pojo.response.WeatherResult
import com.example.weather.presentation.base.BaseActivity
import com.google.android.gms.location.*
import pub.devrel.easypermissions.EasyPermissions
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLastLocation: Location
    private lateinit var mainBinding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModelWeatherCurrent: WeatherCurrentViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherCurrentViewModel::class.java)
    }
    private val viewModelWeather5days: Weather5daysViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(Weather5daysViewModel::class.java)
    }
    private val viewModelGeoPositionSearch: GeoPositionSearchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GeoPositionSearchViewModel::class.java)
    }

    private var latitude: String? = null
    private var longitude: String? = null
    private var locationManager: LocationManager? = null
    private var geoPositionSearch = GeoPositionSearch()
    private var weatherCurent = WeatherCurent()

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        requestPermission()

        viewModelGeoPositionSearch.resultGeoPositionSearch.observe(this, Observer {
            geoPositionSearch = it
            mainBinding.geoPositionSearch = geoPositionSearch
            viewModelWeatherCurrent.getDataWeatherCurrent(it.key.toString(), Key)
            viewModelWeather5days.getDataWeather5days(it.key.toString(), Key)
        })

        viewModelWeather5days.weather5days.observe(this, Observer {
            setDataWeather5days(it)
        })

        viewModelWeatherCurrent.weatherCurrent.observe(this, Observer {
            setDataWeatherCurrent(it)
        })

        viewModelGeoPositionSearch.isLoad.observe(this, Observer {
            showOrHideProgressDialog(it)
        })

        viewModelWeatherCurrent.isLoad.observe(this, Observer {
            showOrHideProgressDialog(it)
        })

        viewModelWeather5days.isLoad.observe(this, Observer {
            showOrHideProgressDialog(it)
        })

    }


    @SuppressLint("SimpleDateFormat")
    private fun setDataWeather5days(weather: WeatherResult) {
        val weatherResult = weather
        mainBinding.weatherResult = weatherResult

        val dateInStringDay1 = weatherResult.DailyForecasts!![1].date
        val dateInStringDay2 = weatherResult.DailyForecasts[2].date
        val dateInStringDay3 = weatherResult.DailyForecasts[3].date
        val dateInStringDay4 = weatherResult.DailyForecasts[4].date

        val sdf5days = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val shortdateInStringDay1 = dateInStringDay1?.substring(0, 19)
        val shortdateInStringDay2 = dateInStringDay2?.substring(0, 19)
        val shortdateInStringDay3 = dateInStringDay3?.substring(0, 19)
        val shortdateInStringDay4 = dateInStringDay4?.substring(0, 19)

        val sdf5days1 = SimpleDateFormat("EEE")
        val day1 = sdf5days.parse(shortdateInStringDay1.toString())
        val day2 = sdf5days.parse(shortdateInStringDay2.toString())
        val day3 = sdf5days.parse(shortdateInStringDay3.toString())
        val day4 = sdf5days.parse(shortdateInStringDay4.toString())

        val resultDay = ArrayList<String>()
        resultDay.add(sdf5days1.format(day1))
        resultDay.add(sdf5days1.format(day2))
        resultDay.add(sdf5days1.format(day3))
        resultDay.add(sdf5days1.format(day4))

        val dayCustom = DayCustom(resultDay)
        mainBinding.dayCustom = dayCustom
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDataWeatherCurrent(listweather: List<WeatherCurent>) {
        weatherCurent = listweather[0]
        mainBinding.weatherCurent = weatherCurent

        val dateInStringCurrent = listweather[0].localObservationDateTime
        val sdfCurrent = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val shortdateInStringCurrent = dateInStringCurrent?.substring(0, 19)
        val date = sdfCurrent.parse(shortdateInStringCurrent.toString())

        val sdfCurrentDate = SimpleDateFormat("yyyy EEEE MMMM-dd")
        val splitString = " "
        val part = sdfCurrentDate.format(date).split(splitString)
        val dateTimeCustom = DateTimeCustom(part[0], part[1] + part[2], part[3] + part[4])
        mainBinding.dateTimeCustom = dateTimeCustom
    }

    private fun requestPermission() {
        if (EasyPermissions.hasPermissions(this, *ACCESS_FINE_LOCATION))
        {
            // Have permissions, do the thing
            mLocationRequest = LocationRequest()
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            //Check gps is enable or not

            if (!locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                //Write Function To enable gps
               onGPS()
            } else {
                //GPS is already On then
                startLocationUpdates()
            }

        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.access_fine_location),
                RC_ACCESS_FINE_LOCATION,
                *ACCESS_FINE_LOCATION
            )
        }
    }

    private fun onGPS() {

        val builder = AlertDialog.Builder(this)

        builder.setMessage(getString(R.string.enable_gps)).setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }

        val alertDialog = builder.create()

        alertDialog.show()
    }

    private fun startLocationUpdates() {
        // Create the location request to start receiving updates

        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval =
            INTERVAL
        mLocationRequest.fastestInterval =
            FASTEST_INTERVAL

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        fusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // do work here
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    private fun onLocationChanged(location: Location) {
        // New location has now been determined

        mLastLocation = location
        latitude = mLastLocation.latitude.toString()
        longitude = mLastLocation.longitude.toString()

        viewModelGeoPositionSearch.getDataGeoPositionSearch(Key, "${latitude},${longitude}")
    }

    private fun convertFahrenheitToCelcius(fahrenheit: Float): Float {
        return (fahrenheit - 32) * 5 / 9
    }

    companion object {

        val ACCESS_FINE_LOCATION = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        const val RC_ACCESS_FINE_LOCATION = 4466
        const val INTERVAL: Long = 2000000
        const val FASTEST_INTERVAL: Long = 1000000

    }

}
