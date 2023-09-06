package com.example.recyclerview


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.lang.Error

class MyForegroundServices: Service() {

    private val CHANNEL_ID = "ForegroundService Kotlin"

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try{
            val input=intent?.getStringExtra("inputExtra")
            createNotificationChannel()
            val notificationIntent = Intent(this,ForegroundServices::class.java)
            val pendingIntent= PendingIntent.getActivity(this, 0,notificationIntent, PendingIntent.FLAG_IMMUTABLE)
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service Notification")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .build()
            startForeground(1,notification)
            Toast.makeText(this,"onStartCommand Implemented",Toast.LENGTH_SHORT).show()
        }catch (e: Error){
            e.printStackTrace()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID,"Foreground Service Channel",NotificationManager.IMPORTANCE_HIGH)
            val manager= getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
            Toast.makeText(this,"Notification Channel Implemented",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this,"SDK VERSION IS LOW",Toast.LENGTH_SHORT).show()
        }
    }
}