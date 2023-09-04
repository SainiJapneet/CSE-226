package com.example.recyclerview

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import android.os.Parcel
import android.os.SystemClock
import android.util.Log
import android.widget.Chronometer
import java.io.FileDescriptor

class MyBoundService: Service() {

    private val LOG_TAG = "BoundService"
    private val mBinder: IBinder = MyBinder()
    private val mChronometer: Chronometer? = null
    override fun onCreate() {
        super.onCreate()
        Log.v(LOG_TAG,"in onCreate()")
        mChronometer!!.setBase(SystemClock.elapsedRealtime())
        mChronometer!!.start()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onRebind(intent: Intent?) {
        Log.v(LOG_TAG,"in onRebind()")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.v(LOG_TAG,"in onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        Log.v(LOG_TAG,"in onDestroy()")
        super.onDestroy()
        mChronometer!!.stop()
    }

    fun getTimeStamp():String?{
        val elapsedMillis = (SystemClock.elapsedRealtime() - mChronometer!!.base)
        val hours = (elapsedMillis/3600000).toInt()
        val minutes = (elapsedMillis - hours * 3600000).toInt() / 60000
        val seconds = (elapsedMillis - hours * 3600000 - minutes * 60000).toInt() / 1000
        val millis = (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000).toInt()
        return "$hours:$minutes:$seconds:$millis"
    }

    inner class MyBinder: Binder(){
        fun getService(): MyBoundService{
            return this@MyBoundService
        }
    }
}