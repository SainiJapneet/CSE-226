package com.example.recyclerview.UNIT_4.PlacementPortal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.recyclerview.R

class StudentAdapter(var ctx: Context, var resource: Int, var list: List<Student>):ArrayAdapter<Student>(ctx, resource, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resource, null)
        val regNo: TextView = view.findViewById(R.id.txtStRegNo)
        val stName: TextView = view.findViewById(R.id.txtStName)
        val txtCGPA: TextView = view.findViewById(R.id.txtCGPA)
        val txtReAppear: TextView = view.findViewById(R.id.txtReAppear)
        val txtSpec: TextView = view.findViewById(R.id.txtSpecialization)
        val data: Student = list[position]
        regNo.text = data.regNo.toString()
        stName.text = data.name
        txtCGPA.text = data.cgpa.toString()
        txtReAppear.text = data.reappear.toString()
        txtSpec.text = data.spec
        return view
    }
}