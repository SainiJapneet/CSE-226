package com.example.recyclerview.UNIT_2

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.recyclerview.R

class JobService : AppCompatActivity() {

    var jobScheduler: JobScheduler? = null
    lateinit var btnStartJob: Button
    lateinit var btnStopJob: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_service)

        btnStartJob = findViewById(R.id.btnStartJob)
        btnStopJob = findViewById(R.id.btnStopJob)

        btnStartJob.setOnClickListener{
            jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
            val componentName = ComponentName(this, JobServiceDemo::class.java)

            val builder = JobInfo.Builder(123, componentName)
            builder.setMinimumLatency(1000)
            builder.setOverrideDeadline(3000)
            builder.setPersisted(true)
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            builder.setRequiresCharging(false)
            jobScheduler!!.schedule(builder.build())
        }

        btnStopJob.setOnClickListener {
            if(jobScheduler != null){

                jobScheduler!!.cancel(123)
                jobScheduler = null
                Toast.makeText(this,"Job terminated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}