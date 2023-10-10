package com.example.servicedemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class ForegroundDemo1 : AppCompatActivity() {
    lateinit var btnStart: Button
    lateinit var btnStop:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_demo1)
        btnStart=findViewById(R.id.buttonStart)
        btnStop=findViewById(R.id.buttonStop)
        btnStart.setOnClickListener {
            val startIntent = Intent(this, ForegroundServiceDemo::class.java)
            startIntent.putExtra("inputExtra", "Foreground Service is running...")
            ContextCompat.startForegroundService(this, startIntent)
        }
        btnStop.setOnClickListener {
            val stopIntent = Intent(this, ForegroundServiceDemo::class.java)
            stopService(stopIntent)
        }
    }

}