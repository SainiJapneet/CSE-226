package com.example.servicedemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class AlarmManagerBroadcast1 : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var mp = MediaPlayer.create(context,  R.raw.alarm)
        Log.d("Hello","repeating alarm")
        mp.start()
        Toast.makeText(context, "Message", Toast.LENGTH_LONG).show()
    }
}