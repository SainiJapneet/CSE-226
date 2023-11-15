package com.example.recyclerview.UNIT_5.Maps

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.recyclerview.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MapActivity : AppCompatActivity() {

    lateinit var txtLongitude: TextView
    lateinit var txtLatitude: TextView
    lateinit var txtCountry: TextView
    lateinit var txtLocality: TextView
    lateinit var txtAddress: TextView
    lateinit var btnGetLocation: Button
    lateinit var btnGetOnMaps: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    var longitude = ""
    var latitude = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        txtLongitude = findViewById(R.id.txtLongitude)
        txtLatitude = findViewById(R.id.txtLatitude)
        txtCountry = findViewById(R.id.txtCountryName)
        txtLocality = findViewById(R.id.txtLocality)
        txtAddress = findViewById(R.id.txtAddress)
        btnGetLocation =findViewById(R.id.btnGetLocation)
        btnGetOnMaps = findViewById(R.id.btnGetOnMaps)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btnGetLocation.setOnClickListener {
            getLocation()
        }
        btnGetOnMaps.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${latitude},${longitude}"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

    }
    @SuppressLint("MissingPermission","SetTextI18n")
    private fun getLocation(){
        if(checkPermissions()){
            if(isLocationEnabled()){
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    location: Location? -> location?.let{
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> = geocoder.getFromLocation(location.latitude,location.latitude, 1)!!
                        longitude = "${list[0].longitude}"
                        latitude = "${list[0].latitude}"
                        txtLatitude.text = "Latitude\n ${list[0].latitude}"
                        txtLongitude.text = "Longitude\n ${list[0].longitude}"
                        txtCountry.text = "Country\n ${list[0].countryName}"
                        txtLocality.text = "Locality\n ${list[0].locality}"
                        txtAddress.text = "Address\n ${list[0].getAddressLine(0)}"
                    }
                }
            }else{
                Toast.makeText(this,"Location Off", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }else{
            requestPermissions()
        }
    }
    private fun isLocationEnabled(): Boolean{
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(
                    LocationManager.NETWORK_PROVIDER
                )
    }

    private fun checkPermissions(): Boolean{
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions(){
        ActivityCompat.requestPermissions(this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            permissionId
        )
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == permissionId){
            if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                getLocation()
            }
        }
    }
}