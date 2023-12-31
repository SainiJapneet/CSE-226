package com.example.recyclerview.UNIT_2

import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class UnBoundServiceDemo: android.app.Service(){
    lateinit var mediaPlayer: MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer = MediaPlayer.create(this,Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer.start()
        mediaPlayer.isLooping = true

        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }
}