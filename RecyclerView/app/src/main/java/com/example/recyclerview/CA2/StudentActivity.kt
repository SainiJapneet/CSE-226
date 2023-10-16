package com.example.recyclerview.CA2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.room.Room
import com.example.recyclerview.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentActivity : AppCompatActivity() {

    lateinit var edtID: EditText
    lateinit var edtName: EditText
    lateinit var edtHostelFee: EditText
    lateinit var edtSemFee: EditText
    lateinit var edtMessFee: EditText
    lateinit var btnAdd: Button
    lateinit var btnDisplay: Button
    lateinit var lstView: ListView
    lateinit var database: StudentDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        edtID = findViewById(R.id.edtID)
        edtName = findViewById(R.id.edtName)
        edtHostelFee = findViewById(R.id.edtHostelFee)
        edtSemFee = findViewById(R.id.edtSemFee)
        edtMessFee = findViewById(R.id.edtMessFee)
        btnAdd = findViewById(R.id.btnAdd)
        btnDisplay = findViewById(R.id.btnDisplay)
        lstView = findViewById(R.id.lstView)
        database = Room.databaseBuilder(applicationContext, StudentDatabase::class.java,"studentDatabase").build()

        btnAdd.setOnClickListener {
            GlobalScope.launch {
                database.studentDAO_1().insert(StudentModel(edtID.text.toString().toLong(),
                    edtName.text.toString(),
                    edtHostelFee.text.toString().toFloat(),
                    edtSemFee.text.toString().toFloat(),
                    edtMessFee.text.toString().toFloat()
                )
                )
            }
        }

        btnDisplay.setOnClickListener {
            displayData()
        }

        lstView.setOnItemClickListener { parent, view, position, id ->

            val view = parent.get(position)
            val status = view.findViewById<TextView>(R.id.txtStatus).text.toString()
            if(status.equals("Pending")){
                var id = view.findViewById<TextView>(R.id.txtID).text.toString().toLong()
                var name = view.findViewById<TextView>(R.id.txtName).text.toString()
                var hostelFee = view.findViewById<TextView>(R.id.txtHostelFee).text.toString().toFloat()
                var semFee = view.findViewById<TextView>(R.id.txtSemFee).text.toString().toFloat()
                var messFee = view.findViewById<TextView>(R.id.txtMessFee).text.toString().toFloat()


                var builder = AlertDialog.Builder(this)
                builder.setTitle("Pay Fee")
                var linearLayout = LinearLayout(this)
                linearLayout.orientation = LinearLayout.VERTICAL



                val edtHostelFee = EditText(this)
                edtHostelFee.hint = "Hostel Fee"
                val edtSemFee = EditText(this)
                edtSemFee.hint = "Sem Fee"
                val edtMessFee = EditText(this)
                edtMessFee.hint = "Mess Fee"


                linearLayout.addView(edtHostelFee)
                linearLayout.addView(edtSemFee)
                linearLayout.addView(edtMessFee)

                builder.setView(linearLayout)

                builder.setPositiveButton("Pay",DialogInterface.OnClickListener { dialog, which ->
                    val hostelFee1 = edtHostelFee.text.toString().toFloatOrNull()
                    val semFee1 = edtSemFee.text.toString().toFloatOrNull()
                    val messFee1 = edtMessFee.text.toString().toFloatOrNull()

                    if(hostelFee1 != null && semFee1 != null && messFee1 != null){
                        val updatedHostelFee = hostelFee - hostelFee1!!
                        val updatedSemFee = semFee - semFee1!!
                        val updatedMessFee = messFee - messFee1!!
                        if (updatedHostelFee < 0 || updatedSemFee < 0 || updatedMessFee < 0) {
                            edtHostelFee.error = "Invalid value"
                            edtSemFee.error = "Invalid value"
                            edtMessFee.error = "Invalid value"
                        }else{
                            GlobalScope.launch {
                                database.studentDAO_1().update(StudentModel(id,name,updatedHostelFee,updatedSemFee,updatedMessFee))
                            }
                            displayData()
                        }

                    }else{
                        Toast.makeText(this,"Invalid input, Numeric value expected", Toast.LENGTH_SHORT).show()
                    }
                })
                builder.setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                builder.show()





            }else{
                Toast.makeText(this,"Fee already paid",Toast.LENGTH_SHORT).show()
            }
        }

        lstView.setOnItemLongClickListener { parent, view, position, id ->
            val view = parent.get(position)
            val status = view.findViewById<TextView>(R.id.txtStatus).text.toString()
            if(status.equals("Pending")){
                Toast.makeText(this,"Pay whole fee to generate receipt",Toast.LENGTH_LONG).show()
            }else{
                var id = view.findViewById<TextView>(R.id.txtID).text.toString().toLong()
                var name = view.findViewById<TextView>(R.id.txtName).text.toString()

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Payment Receipt")

                val linearLayout = LinearLayout(this)
                linearLayout.orientation = LinearLayout.VERTICAL
                linearLayout.gravity = Gravity.CENTER
                val padding = resources.getDimensionPixelSize(androidx.constraintlayout.widget.R.dimen.abc_action_button_min_width_material)

                val txtName = TextView(this)
                txtName.text = "Name: $name"
                val txtID = TextView(this)
                txtID.text = "ID: $id"
                val txtFee = TextView(this)
                txtFee.text = "Fee Status : PAID"
                txtFee.textSize = 20F

                linearLayout.addView(txtID)
                linearLayout.addView(txtName)
                linearLayout.addView(txtFee)

                builder.setView(linearLayout)
                builder.setPositiveButton("OK", null)
                val dialog = builder.create()
                dialog.show()
            }
            return@setOnItemLongClickListener true
        }

    }

    fun displayData(){
        database.studentDAO_1().getData().observe(this){
            val adapter = MyAdapter(this, R.layout.custom_layout,it)
            lstView.adapter = adapter
        }
    }

}