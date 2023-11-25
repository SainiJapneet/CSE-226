package com.example.recyclerview.UNIT_2

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import android.widget.Toast

class JobServiceDemo: JobService(){
    override fun onStartJob(p0: JobParameters?): Boolean {
        val intent = Intent(this@JobServiceDemo, JobServiceBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 234, intent, PendingIntent.FLAG_IMMUTABLE)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),pendingIntent)
        Toast.makeText(this,"Alarm Set", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("TAG","In onStopJob")
        return true
    }

}