package com.example.recyclerview.UNIT_2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import com.example.recyclerview.R

class JobServiceBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val mp = MediaPlayer.create(p0, R.raw.guitar)
        Log.d("TAG","Repeating alarm")
        mp.start()
        Toast.makeText(p0,"Alarm",Toast.LENGTH_SHORT).show()
    }
}