package com.example.recyclerview.UNIT_4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.recyclerview.R

class EmpSQL_DB : AppCompatActivity() {

    lateinit var edtID: EditText
    lateinit var edtName: EditText
    lateinit var edtSalary: EditText
    lateinit var edtExperience: EditText
    lateinit var btnCreate: Button
    lateinit var btnRetrieve: Button
    lateinit var btnUpdate: Button
    lateinit var btnDelete: Button
    lateinit var txtID: TextView
    lateinit var txtName: TextView
    lateinit var txtSalary: TextView
    lateinit var txtExperience: TextView
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_sql_db)

        edtID = findViewById(R.id.edtEmpID)
        edtName = findViewById(R.id.edtEmpName)
        edtSalary = findViewById(R.id.edtEmpSalary)
        edtExperience = findViewById(R.id.edtExperience)
        btnCreate = findViewById(R.id.btnCreateEmp)
        btnRetrieve = findViewById(R.id.btnRetrieveEmp)
        btnUpdate = findViewById(R.id.btnUpdateEmp)
        btnDelete = findViewById(R.id.btnDeleteEmp)
        txtID = findViewById(R.id.txtEmpID)
        txtName = findViewById(R.id.txtEmpName)
        txtSalary = findViewById(R.id.txtEmpSalary)
        txtExperience = findViewById(R.id.txtEmpExperience)

        fun retrieveData(salary: String?, exp: String?){
            val db = EmpSQL_DB_Demo(this, null)
            val cursor = db.printEmp(salary, exp)
            txtID.text = "ID \n\n"
            txtName.text = "Name \n\n"
            txtSalary.text = "Age \n\n"
            txtExperience.text = "Experience \n\n"
            cursor!!.moveToFirst()
            txtID.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.ID_COL)) + "\n")
            txtName.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.NAME_COL)) + "\n")
            txtSalary.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.SALARY_COL)) + "\n")
            txtExperience.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.EXPERIENCE_COl)) + "\n")
            while (cursor.moveToNext()){
                txtID.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.ID_COL)) + "\n")
                txtName.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.NAME_COL)) + "\n")
                txtSalary.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.SALARY_COL)) + "\n")
                txtExperience.append(cursor.getString(cursor.getColumnIndex(EmpSQL_DB_Demo.EXPERIENCE_COl)) + "\n")
            }
            cursor.close()
        }
        btnCreate.setOnClickListener {
            val db = EmpSQL_DB_Demo(this, null)

            val id = edtID.text.toString()
            val name = edtName.text.toString()
            val salary = edtSalary.text.toString()
            val exp = edtExperience.text.toString()

            edtID.setText("")
            edtName.setText("")
            edtSalary.setText("")
            edtExperience.setText("")

            db.addEmp(id, name, salary, exp)
            retrieveData("","")
            Toast.makeText(this,"Emp created",Toast.LENGTH_SHORT).show()
        }

        btnRetrieve.setOnClickListener {
           retrieveData(edtSalary.text.toString(),edtExperience.text.toString())
        }

        btnUpdate.setOnClickListener {
            val db = EmpSQL_DB_Demo(this, null)
            val id = edtID.text.toString()
            val name = edtName.text.toString()
            val salary = edtSalary.text.toString()
            val exp = edtExperience.text.toString()
            db.updateEmp(id,name,salary, exp)
            edtID.setText("")
            edtName.setText("")
            edtSalary.setText("")
            edtExperience.setText("")
            retrieveData("","")
        }

        btnDelete.setOnClickListener {
            val db = EmpSQL_DB_Demo(this, null)
            db.deleteEmp(edtID.text.toString())
            edtID.setText("")
            edtName.setText("")
            edtSalary.setText("")
            edtExperience.setText("")
            retrieveData("","")
        }

    }
}