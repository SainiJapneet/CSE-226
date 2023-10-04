package com.example.recyclerview.UNIT_4.RoomDatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.recyclerview.R

class MyAdapter(var ctx: Context, var resources: Int, var list: List<Contact>): ArrayAdapter<Contact>(ctx,resources,list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resources, null)
        val id: TextView = view.findViewById(R.id.txtIDDB1)
        val name: TextView = view.findViewById(R.id.txtNameDB1)
        val phone: TextView = view.findViewById(R.id.txtPhoneDB1)
        val data: Contact = list[position]
        id.text = data.id.toString()
        name.text = data.name
        phone.text = data.phone.toString()
        return view
    }
}