package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ForegroundServices : AppCompatActivity() {

    lateinit var btnStartService:Button
    lateinit var btnStopService: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_services)

        btnStartService = findViewById(R.id.btnStartService)
        btnStopService = findViewById(R.id.btnStopService)

        btnStartService.setOnClickListener {
            val intent = Intent(this,MyForegroundServices::class.java)
            intent.putExtra("inputExtra","Foreground service in progress")
            ContextCompat.startForegroundService(this,intent)
        }

        btnStopService.setOnClickListener {
            val intent = Intent(this,MyForegroundServices::class.java)
            stopService(intent)
        }
    }
}