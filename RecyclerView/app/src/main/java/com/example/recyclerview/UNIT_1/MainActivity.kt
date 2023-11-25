package com.example.recyclerview.UNIT_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    var arrList = ArrayList<MyDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        arrList.add(MyDataModel("Semester 1", "GPA", "2018"))
        arrList.add(MyDataModel("Semester 2", "GPA", "2019"))
        arrList.add(MyDataModel("Semester 3", "GPA", "2019"))
        arrList.add(MyDataModel("Semester 4", "GPA", "2020"))

        // Set up RecyclerView Adapter
        val adapter = MyAdapter(arrList)
        recyclerView.adapter = adapter
    }
}
