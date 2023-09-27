package com.example.recyclerview.UNIT_4

import android.annotation.SuppressLint
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
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button
    lateinit var btnPrintData: Button
    lateinit var txtNameDb: TextView
    lateinit var txtAgeDb: TextView
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_sql)

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        btnAddData = findViewById(R.id.btnAddData)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
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

        btnUpdate.setOnClickListener {
            val db = Db_Sql_Demo(this, null)
            val name = edtName.text.toString()
            val age = edtAge.text.toString()

            db.updateData(name, age)

            edtName.setText("")
            edtAge.setText("")


        }

        btnDelete.setOnClickListener {
            val db = Db_Sql_Demo(this, null)
            db.deleteData()
        }

        btnPrintData.setOnClickListener {
            val db = Db_Sql_Demo(this, null)
            val cursor = db.getData()
            txtNameDb.text = "Name \n\n"
            txtAgeDb.text = "Age \n\n"
            cursor!!.moveToFirst()
            txtNameDb.append(cursor.getString(cursor.getColumnIndex(Db_Sql_Demo.NAME_COL)) + "\n")
            txtAgeDb.append(cursor.getString(cursor.getColumnIndex(Db_Sql_Demo.AGE_COL)) + "\n")
            while (cursor.moveToNext()){
                txtNameDb.append(cursor.getString(cursor.getColumnIndex(Db_Sql_Demo.NAME_COL)) + "\n")
                txtAgeDb.append(cursor.getString(cursor.getColumnIndex(Db_Sql_Demo.AGE_COL)) + "\n")
            }
            cursor.close()
        }
    }
}