package com.example.recyclerview.CA2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.recyclerview.R

class MyAdapter(var ctx: Context, var res: Int, var list: List<StudentModel>): ArrayAdapter<StudentModel>(ctx, res, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(res, null)
        val txtID: TextView = view.findViewById(R.id.txtID)
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtHostelFee: TextView = view.findViewById(R.id.txtHostelFee)
        val txtSemFee: TextView = view.findViewById(R.id.txtSemFee)
        val txtMessFee: TextView = view.findViewById(R.id.txtMessFee)
        val txtStatus: TextView = view.findViewById(R.id.txtStatus)

        val data: StudentModel = list[position]
        txtID.text = data.id.toString()
        txtName.text = data.name
        txtHostelFee.text = data.hostelFee.toString()
        txtSemFee.text = data.semFee.toString()
        txtMessFee.text = data.messFee.toString()
        if(data.hostelFee == 0f && data.semFee == 0f && data.messFee == 0f){
            txtStatus.text = "Paid"
        }else{
            view.setBackgroundColor(ContextCompat.getColor(ctx, R.color.red_bg))
            txtStatus.text = "Pending"
        }
        return view
    }
}