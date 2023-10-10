package com.example.servicedemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var start: Button
    lateinit var stop: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start = findViewById(R.id.startButton)
        stop = findViewById(R.id.stopButton)
        start.setOnClickListener{

            startService(Intent(this, MyService1::class.java))
        }
        stop.setOnClickListener{
            stopService(Intent(this, MyService1::class.java))
        }
    }


}