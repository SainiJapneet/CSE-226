package com.example.recyclerview.UNIT_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import com.example.recyclerview.R

class ForegroundService : AppCompatActivity() {

    lateinit var btnStartService:Button
    lateinit var btnStopService: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_services)

        btnStartService = findViewById(R.id.btnPrintTime)
        btnStopService = findViewById(R.id.btnStopBound)

        btnStartService.setOnClickListener {
            val intent = Intent(this, ForegroundServiceDemo::class.java)
            intent.putExtra("inputExtra","Foreground service in progress")
            ContextCompat.startForegroundService(this,intent)
        }

        btnStopService.setOnClickListener {
            val intent = Intent(this, ForegroundServiceDemo::class.java)
            stopService(intent)
        }
    }
}