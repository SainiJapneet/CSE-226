package com.example.customlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var lstView: ListView
    lateinit var button: Button

    var arrList= ArrayList<NewDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lstView = findViewById(R.id.lstView)
        button = findViewById(R.id.button)


        arrList.add(NewDataModel("XYZ","Co-worker", R.drawable.baseline_person_24,false))
        arrList.add(NewDataModel("ABC","Driver", R.drawable.baseline_person_24, false))
        arrList.add(NewDataModel("IJK","Neighbor", R.drawable.baseline_person_outline_24, false))
        lstView.adapter = MyAdapter(this, R.layout.custom_row, arrList)
        button.setOnClickListener {
            var str = "Checked Items: "
            for(i in arrList){
                if(i.checked){
                    str+=" ${i.title}"
                }
            }
            Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
        }
    }
}