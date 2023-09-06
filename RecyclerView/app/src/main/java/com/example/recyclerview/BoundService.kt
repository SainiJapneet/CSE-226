package com.example.recyclerview

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView


class BoundService : AppCompatActivity() {

    lateinit var txtTime: TextView
    lateinit var btnPrintTime: Button
    lateinit var btnStopBound: Button

    var mBoundService: MyBoundService? = null
    var mServiceBound = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
/*
        txtTime = findViewById(R.id.txtTime)
        btnPrintTime = findViewById(R.id.btnPrintTime)
        btnStopBound = findViewById(R.id.btnStopBound)

        btnPrintTime.setOnClickListener {
            if(mServiceBound){
                txtTime.setText(mBoundService!!.getTimeStamp())
            }
        }
        btnStopBound.setOnClickListener {
            if(mServiceBound){
                unbindService(mServiceConnection)
                mServiceBound = false
            }
            val intent = Intent(this@BoundService,MyBoundService::class.java)
            stopService(intent)
        }
        override fun onStart(){
            super.onStart()
            val intent = Intent(this, MyBoundService::class.java)
            startService(intent)
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        }
        override fun onStop(){
            super.onStop()
            if(mServiceBound){
                unbindService(mServiceConnection)
                mServiceBound = false
            }
        }
        val mServiceConnection: ServiceConnection = object: ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
                mServiceBound = false
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val myBinder: MyBoundService.MyBinder = service as MyBoundService.MyBinder
                mBoundService = myBinder.getService()
                mServiceBound = true
            }
        }

 */
    }
}