package com.example.servicedemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.util.Log
import android.widget.Toast

class JobServiceClassDemo1 : JobService() {
    override fun onStartJob(p0: JobParameters?): Boolean
    {
        Log.d("TAG", "onStartJob:")
        val intent = Intent(this@JobServiceClassDemo1,
            AlarmManagerBroadcast1::class.java   )
        val pendingIntent = PendingIntent.getBroadcast(this,
            234, intent, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()
            ,pendingIntent)
        Toast.makeText(this@JobServiceClassDemo1, "Alarm Set "
            , Toast.LENGTH_LONG).show()
        return true
    }
    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.d("TAG", "onStopJob:")
        return true
    }
}