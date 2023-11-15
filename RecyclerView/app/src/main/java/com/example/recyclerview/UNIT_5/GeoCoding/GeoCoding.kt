package com.example.recyclerview.UNIT_5.GeoCoding

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.recyclerview.R

class GeoCoding : AppCompatActivity() {
    lateinit var edtAddress: EditText
    lateinit var btnGetLocCord: Button
    lateinit var btnOpenMaps: Button
    lateinit var txtLatitude1: TextView
    lateinit var txtLongitude1: TextView
    var longitude1 =""
    var latitude1 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geo_coding)
        edtAddress = findViewById(R.id.edtAddress)
        btnGetLocCord = findViewById(R.id.btnGetCord)
        btnOpenMaps = findViewById(R.id.btnOpenMap)
        txtLatitude1 = findViewById(R.id.txtLatitude1)
        txtLongitude1 = findViewById(R.id.txtLongitude1)

        btnGetLocCord.setOnClickListener {
            if(edtAddress.text.toString().isEmpty()){
                Toast.makeText(this,"Enter address",Toast.LENGTH_SHORT).show()
            }else{
                getLocationFromAddress(edtAddress.text.toString())
            }
        }
        btnOpenMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${latitude1},${longitude1}"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }


    }
    fun getLocationFromAddress(address: String){
        val geocoder = Geocoder(this)
        val list: List<Address> = geocoder.getFromLocationName(address, 5)!!
        if(list.isNullOrEmpty()) {
            return
        }

        latitude1 = "${list[0].latitude}"
        longitude1 = "${list[0].longitude}"
        txtLatitude1.text = "${list[0].latitude}"
        txtLongitude1.text = "${list[0].longitude}"
    }
}