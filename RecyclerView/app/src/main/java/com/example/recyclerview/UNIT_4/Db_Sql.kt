package com.example.recyclerview.UNIT_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.recyclerview.R

class Db_Sql : AppCompatActivity() {

    lateinit var edtName: EditText
    lateinit var edtAge: EditText
    lateinit var btnAddData: Button
    lateinit var btnPrintData: Button
    lateinit var txtNameDb: TextView
    lateinit var txtAgeDb: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_sql)

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        btnAddData = findViewById(R.id.btnAddData)
        btnPrintData = findViewById(R.id.btnPrintData)
        txtNameDb = findViewById(R.id.txtNameDB)
        txtAgeDb = findViewById(R.id.txtAgeDB)

        btnAddData.setOnClickListener {
            val db = Db_Sql_Demo(this, null)

            val name = edtName.text.toString()
            val age = edtAge.text.toString()

            db.addData(name, age)
            edtName.setText("")
            edtAge.setText("")
            Toast.makeText(this, "Name : $name added to DB", Toast.LENGTH_SHORT).show()
        }

        btnPrintData.setOnClickListener {
            val db = Db_Sql_Demo(this, null)
            val data = db.getData()
            Log.d("getData() status","Data: $data")
            println(data)
            Toast.makeText(this, "Data: $data", Toast.LENGTH_SHORT).show()
        }
    }
}