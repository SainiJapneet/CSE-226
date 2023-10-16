package com.example.recyclerview.CA2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private var adapter: MyAdapter? = null
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
            val selectedItem: StudentModel? = adapter?.getItem(position)

            val view = parent.get(position)
            val status = view.findViewById<TextView>(R.id.txtStatus).text.toString()
            if(status.equals("Pending")){
                var id = 0L
                var name = ""
                var hostelFee = 0F
                var semFee = 0F
                var messFee = 0F

                if(selectedItem != null){
                    id = selectedItem.id
                    name = selectedItem.name
                    hostelFee = selectedItem.hostelFee
                    semFee = selectedItem.semFee
                    messFee = selectedItem.messFee
                }

                var builder = AlertDialog.Builder(this)
                builder.setTitle("Pay Fee")
                var linearLayout = LinearLayout(this)
                linearLayout.orientation = LinearLayout.VERTICAL

                val txtHostelFee = TextView(this)
                txtHostelFee.textSize = 18F
                txtHostelFee.setText("Hostel Fee : $hostelFee")
                val txtSemFee = TextView(this)
                txtSemFee.setText("Semester Fee : $semFee")
                txtSemFee.textSize = 18F
                val txtMessFee = TextView(this)
                txtMessFee.setText("Mess Fee : $messFee")
                txtMessFee.textSize = 18F

                val edtHostelFee = EditText(this)
                edtHostelFee.hint = "Pay Hostel Fee"
                val edtSemFee = EditText(this)
                edtSemFee.hint = "Pay Sem Fee"
                val edtMessFee = EditText(this)
                edtMessFee.hint = "Pay Mess Fee"

                linearLayout.addView(txtHostelFee)
                linearLayout.addView(txtSemFee)
                linearLayout.addView(txtMessFee)
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
    }

    fun displayData(){
        database.studentDAO_1().getData().observe(this){
            val adapter = MyAdapter(this, R.layout.custom_layout,it)
            lstView.adapter = adapter
        }
    }

}