package com.example.recyclerview.UNIT_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.recyclerview.R

class UnBoundService : AppCompatActivity() {
    lateinit var btnStart: Button
    lateinit var btnStop: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un_bound_services)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnStart.setOnClickListener {
            startService(Intent(this, UnBoundServiceDemo::class.java))
        }
        btnStop.setOnClickListener {
            stopService(Intent(this, UnBoundServiceDemo::class.java))
        }

    }
}