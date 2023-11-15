package com.example.recyclerview.UNIT_4.RoomDatabase

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.room.Room
import com.example.recyclerview.MyAdapter
import com.example.recyclerview.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class RoomDatabase : AppCompatActivity() {
    lateinit var edtID: EditText
    lateinit var edtName: EditText
    lateinit var edtPhone: EditText
    lateinit var btnAdd: Button
    lateinit var btnDisplay: Button
    lateinit var lstViewDB: ListView
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database)

        edtID = findViewById(R.id.edtIDDB)
        edtName = findViewById(R.id.edtNameDB)
        edtPhone = findViewById(R.id.edtPhoneDB)
        btnAdd = findViewById(R.id.btnAddDB)
        btnDisplay = findViewById(R.id.btnDisplayDB)
        lstViewDB = findViewById(R.id.lstViewDB)
        database = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contactDb").build()

        lstViewDB.setOnItemLongClickListener { parent, view, position, id ->
            val view = parent.get(position)
            val id = view.findViewById<TextView>(R.id.txtIDDB1).text.toString().toLong()
            val name = view.findViewById<TextView>(R.id.txtNameDB1).text.toString()
            val phone = view.findViewById<TextView>(R.id.txtPhoneDB1).text.toString().toLong()

            var builder = AlertDialog.Builder(this)
            builder.setTitle("Edit")
            var linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.VERTICAL

            val viewID = EditText(this)
            viewID.setText(id.toString())
            linearLayout.addView(viewID)

            val viewName = EditText(this)
            viewName.setText(name)
            linearLayout.addView(viewName)

            val viewPhone = EditText(this)
            viewPhone.setText(phone.toString())
            linearLayout.addView(viewPhone)

            builder.setView(linearLayout)
            builder.setPositiveButton("Edit",DialogInterface.OnClickListener{ dialog, which ->
                val updatedName = viewName.text.toString()
                //val updatedID = viewID.text.toString().toLong()
                val updatedPhone = viewPhone.text.toString().toLong()
                GlobalScope.launch {
                    database.contactDAO().update(Contact(id,updatedName,updatedPhone))
                }
                Toast.makeText(this,"Data updated",Toast.LENGTH_SHORT).show()
            })
            builder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            builder.show()

            return@setOnItemLongClickListener true
        }

        btnDisplay.setOnClickListener {
            getData(it)
        }

        btnAdd.setOnClickListener {
            GlobalScope.launch {
                database.contactDAO().insert(
                    Contact(edtID.text.toString().toLong(),
                        edtName.text.toString(),
                        edtPhone.text.toString().toLong())
                )
            }
        }

    }
    fun getData(view: View){
            database.contactDAO().getContact().observe(this){
            val adapter = MyAdapter(this, R.layout.custom_layout_room_db,it)
            lstViewDB.adapter = adapter
        }
    }

}