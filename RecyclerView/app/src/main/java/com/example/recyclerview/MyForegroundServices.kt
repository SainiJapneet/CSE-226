package com.example.recyclerview

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class MyForegroundServices: Service() {

    private val CHANNEL_ID = "ForegroundService Kotlin"
    override fun onStart(intent: Intent?, startId: Int) {
        val input=intent?.getStringExtra("inputExtra")
        createNotificationChannel()
        val notificationIntent = Intent(this,ForegroundServices::class.java)
        val pendingIntent= PendingIntent.getActivity(this, 0,notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        super.onStart(intent, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID,"Foreround Service Channel",NotificationManager.IMPORTANCE_DEFAULT)
            val manager= getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }
}