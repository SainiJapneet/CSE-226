package com.example.recyclerview.UNIT_4.RoomDatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.recyclerview.R

open class RoomDatabase : AppCompatActivity() {
    lateinit var edtID: EditText
    lateinit var edtName: EditText
    lateinit var edtPhone: EditText
    lateinit var btnAdd: Button
    lateinit var btnDisplay: Button
    lateinit var lstViewDB: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database)

        edtID = findViewById(R.id.edtIDDB)
        edtName = findViewById(R.id.edtNameDB)
        edtPhone = findViewById(R.id.edtPhoneDB)
        btnAdd = findViewById(R.id.btnAddDB)
        btnDisplay = findViewById(R.id.btnDisplayDB)
        lstViewDB = findViewById(R.id.lstViewDB)
    }
}