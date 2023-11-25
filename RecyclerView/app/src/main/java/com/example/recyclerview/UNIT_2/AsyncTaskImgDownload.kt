package com.example.recyclerview.UNIT_2

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.recyclerview.R
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class AsyncTaskImgDownload : AppCompatActivity() {
    lateinit var btnDownload: Button
    lateinit var imgDownloaded: ImageView

    lateinit var imgURL: URL
    lateinit var progressDialog: ProgressDialog
    var `is`:InputStream? = null
    var bmImg: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task_img_download)
        btnDownload = findViewById(R.id.btnDownload)
        imgDownloaded = findViewById(R.id.imgDownloaded)
        btnDownload.setOnClickListener {
            val asyncTask: MyTaskDownload = MyTaskDownload()
            asyncTask.execute("https://www.cleverfiles.com/howto/wp-content/uploads/2018/03/minion.jpg")
        }
    }


    private inner class MyTaskDownload: AsyncTask<String?, String?, Bitmap?>(){

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@AsyncTaskImgDownload)
            progressDialog.setMessage("Please wait, Download in progress ...")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }
        override fun doInBackground(vararg params: String?): Bitmap? {
            try{
                imgURL = URL(params[0])
                val connection: HttpURLConnection = imgURL.openConnection() as HttpURLConnection
                connection.setDoInput(true)
                connection.connect()
                `is` = connection.getInputStream()
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.RGB_565
                bmImg = BitmapFactory.decodeStream(`is`,null,options)
            }
            catch (e: IOException){
                e.printStackTrace()
            }
            return bmImg
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            if(imgDownloaded != null){
                progressDialog.hide()
                imgDownloaded.setImageBitmap(result)
            }
            else{
                progressDialog.show()
            }
        }
    }
}