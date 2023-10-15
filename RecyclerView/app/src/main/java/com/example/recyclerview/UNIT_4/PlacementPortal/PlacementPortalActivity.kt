package com.example.recyclerview.UNIT_4.PlacementPortal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.recyclerview.R

class PlacementPortalActivity : AppCompatActivity() {
    lateinit var edtStRegNo: EditText
    lateinit var edtStName: EditText
    lateinit var edtStCGPA: EditText
    lateinit var edtStReAppear: EditText
    lateinit var edtStSpec: EditText
    lateinit var btnStAdd: Button
    lateinit var lstViewStudent: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placement_portal)
        edtStRegNo = findViewById(R.id.edtStRegNo)
        edtStName = findViewById(R.id.edtStName)
        edtStCGPA = findViewById(R.id.edtStCGPA)
        edtStReAppear = findViewById(R.id.edtStReAppear)
        edtStSpec = findViewById(R.id.edtStSpec)
    }
}