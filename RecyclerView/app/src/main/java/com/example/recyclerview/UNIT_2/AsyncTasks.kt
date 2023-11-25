package com.example.recyclerview.UNIT_2

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import com.example.recyclerview.R

class AsyncTasks : AppCompatActivity() {
    lateinit var lstView: ListView
    lateinit var progressBar: ProgressBar
    lateinit var arrayAdapter: ArrayAdapter<String>
    var arrList= ArrayList<String>()
    var arr = arrayOf("1","2","3","4","5","6","7","8","9","10")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_tasks)
        lstView = findViewById(R.id.lstView)
        progressBar = findViewById(R.id.progressBar)
        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,arrList)
        lstView.adapter = arrayAdapter
        MyTaskDemo().execute()
    }
    internal inner class MyTaskDemo:AsyncTask<Void?,Int?,String?>(){
        var count = 0
        override fun onPreExecute() {
            progressBar.max = 10
            progressBar.progress = 0
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Void?): String? {
            for(i in 1.. 10) {
                count = count + 1
                publishProgress(i)
                try{
                    Thread.sleep(1000)
                }catch (e:java.lang.Exception){
                    e.printStackTrace()
                }
            }

            return "Task Complete"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.progress = values[0]!!
            arrList.add(arr[count-1])
            arrayAdapter.notifyDataSetChanged()
        }

        override fun onPostExecute(result: String?) {
            Toast.makeText(this@AsyncTasks,result,Toast.LENGTH_SHORT).show()
        }

    }
}