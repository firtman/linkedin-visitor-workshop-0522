package com.linkedin.billing.livisitor.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.linkedin.billing.livisitor.R

class LocationActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        requestPermissions()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val cafeteriaLocation = Location(LocationManager.GPS_PROVIDER)
        cafeteriaLocation.latitude = 37.3920838
        cafeteriaLocation.longitude = -122.04796

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location!=null) {
                    findViewById<TextView>(R.id.txtLocation).text = """
                        You are at ${location?.latitude}, ${location?.longitude}
                        ${location?.distanceTo(cafeteriaLocation)} meters to the Cafeteria
                    """.trimIndent()
                } else {
                    findViewById<TextView>(R.id.txtLocation).text = "Location not acquired"
                }
            }
    }

    fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                    getLocation()

                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted
                // .
                    getLocation()

                } else -> {
                // No location access granted.

            }
            }
        }

        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION))
        }


}