package com.example.weather.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.presentation.base.BaseActivity
import com.google.android.gms.location.*
import pub.devrel.easypermissions.EasyPermissions
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

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        requestPermission()

        viewModelGeoPositionSearch.resultGeoPositionSearch.observe(this, Observer {

            viewModelWeatherCurrent.getDataWeatherCurrent(it.key.toString(), Key)
            viewModelWeather5days.getDataWeather5days(it.key.toString(), Key)
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

        mainBinding.geoPositionSearchViewModel = viewModelGeoPositionSearch
        mainBinding.weatherCurrentViewModel=viewModelWeatherCurrent
        mainBinding.weather5daysViewModel=viewModelWeather5days
        mainBinding.lifecycleOwner = this

    }

    private fun requestPermission() {
        if (EasyPermissions.hasPermissions(this, *ACCESS_FINE_LOCATION)) {
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

    companion object {

        val ACCESS_FINE_LOCATION = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        const val RC_ACCESS_FINE_LOCATION = 4466
        const val INTERVAL: Long = 50000
        const val FASTEST_INTERVAL: Long = 11000

    }

}
