package com.example.recyclerview.CA2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.recyclerview.R

class MyAdapter(var ctx: Context, var res: Int, var list: List<StudentModel>): ArrayAdapter<StudentModel>(ctx, res, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(res, null)
        val txtID: TextView = view.findViewById(R.id.txtID)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtStatus: TextView = view.findViewById(R.id.txtStatus)

        val data: StudentModel = list[position]
        txtID.text = data.id.toString()
        txtName.text = data.name
        if(data.hostelFee == 0f && data.semFee == 0f && data.messFee == 0f){
            txtStatus.text = "Paid"
        }else{
            txtStatus.text = "Pending"
        }
        return view
    }
}