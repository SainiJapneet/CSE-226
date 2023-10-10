package com.example.servicedemo


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BoundServiceActivity1 : AppCompatActivity() {
    var mBoundService: BoundServiceDemo1? = null
    var mServiceBound = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service1)
        val timestampText = findViewById<View>(R.id.timestamp_text) as TextView
        val printTimestampButton: Button = findViewById<View>(R.id.print_timestamp) as Button
        val stopServiceButon: Button = findViewById<View>(R.id.stop_service) as Button
        printTimestampButton.setOnClickListener {
                if (mServiceBound) {
                    timestampText.text = mBoundService!!.getTimestamp()
            }
        }
        stopServiceButon.setOnClickListener {
                if (mServiceBound) {
                    unbindService(mServiceConnection)
                    mServiceBound = false
                }
                val intent = Intent(
                    this@BoundServiceActivity1,
                    BoundServiceDemo1::class.java)
                stopService(intent)
            
            }
        }
    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundServiceDemo1::class.java)
        startService(intent)

        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }
    override fun onStop() {
        super.onStop()
        if (mServiceBound) {
            unbindService(mServiceConnection)
            mServiceBound = false
        }
    }
        val mServiceConnection: ServiceConnection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName) {
                mServiceBound = false
            }
            override fun onServiceConnected(name: ComponentName, service: IBinder)
            {
                val myBinder: BoundServiceDemo1.MyBidner = service as
                        BoundServiceDemo1.MyBidner
                mBoundService = myBinder.getService()
                mServiceBound = true
            }
        }
    }
