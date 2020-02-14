package com.example.weather.ui

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weather.R
import com.example.weather.ui.base.BaseActivity
import com.google.android.gms.location.*
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
     lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModelMain: MainViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mLastLocation: Location

    private var locationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        requestPermission()
        initViewModelMain()
    }

    private fun initViewModelMain() {
        viewModelMain =
            ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java).apply {
                resultGeoPositionSearch.observe(this@MainActivity, Observer { result ->
                    result?.let {

                    }
                })

                loadingStatus.observe(this@MainActivity, Observer { state ->
                    state?.let {
                        showOrHideProgressDialog(it)
                    }
                })
            }
    }

    private fun requestPermission() {
        if (EasyPermissions.hasPermissions(
                this,
                *ACCESS_FINE_LOCATION
            )
        ) {
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

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton(
            "YES"
        ) { _, _ -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                "NO"
            ) { dialog, _ -> dialog.cancel() }
        val alertDialog = builder.create()
        alertDialog.show()
    }


    private fun startLocationUpdates() {

        // Create the location request to start receiving updates

        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = INTERVAL
        mLocationRequest.fastestInterval = FASTEST_INTERVAL

        // Create LocationSettingsRequest object using location request
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest)
        val locationSettingsRequest = builder.build()

        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        // new Google API SDK v11 uses getFusedLocationProviderClient(this)

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
        tv.text =
            "LATITUDE : " + mLastLocation.latitude + "    LONGITUDE : " + mLastLocation.longitude
        viewModelMain.getDataGeoPositionSearch(
            Key,
            "${mLastLocation.latitude},${mLastLocation.longitude}"
        )
        // You can now create a LatLng Object for use with maps
    }

    companion object {
        val ACCESS_FINE_LOCATION = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        const val RC_ACCESS_FINE_LOCATION = 4466
        const val INTERVAL: Long = 2000
        const val FASTEST_INTERVAL: Long = 1000

    }

}
