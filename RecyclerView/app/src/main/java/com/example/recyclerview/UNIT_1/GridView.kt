package com.example.recyclerview.UNIT_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class GridView : AppCompatActivity() {
    lateinit var recyclerView2: RecyclerView
    lateinit var edtIconName: EditText
    lateinit var btnAddIcon: Button
    var arrList = ArrayList<GridModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        recyclerView2 = findViewById(R.id.recyclerView2)
        recyclerView2.setHasFixedSize(true)

        edtIconName = findViewById(R.id.edtIconName)
        btnAddIcon = findViewById(R.id.btnAddIcon)

        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView2.layoutManager = layoutManager



        arrList.add(GridModel("WiFi", R.drawable.wifi))
        arrList.add(GridModel("Profile", R.drawable.profile))
        arrList.add(GridModel("Call", R.drawable.call))
        arrList.add(GridModel("Bluetooth", R.drawable.bluetooth))
        arrList.add(GridModel("Network", R.drawable.network))
        arrList.add(GridModel("Battery", R.drawable.battery))
        btnAddIcon.setOnClickListener {
            arrList.add(GridModel(edtIconName.text.toString(), R.drawable.ic_launcher_foreground))
            val adapter = GridAdapter(this, arrList)
            recyclerView2.adapter = adapter
        }
        val adapter = GridAdapter(this, arrList)
        recyclerView2.adapter = adapter

    }
}