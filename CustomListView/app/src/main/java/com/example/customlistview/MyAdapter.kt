package com.example.customlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MyAdapter(context: Context,var resource: Int,var objects: ArrayList<NewDataModel>): ArrayAdapter<NewDataModel>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(resource, null)

        val imageView: ImageView = view.findViewById(R.id.imgView)
        val txtTitle: TextView = view.findViewById(R.id.txtName)
        val txtDescription: TextView = view.findViewById(R.id.txtDescription)
        val btnRemove: Button = view.findViewById(R.id.btnRemove)
        val checkBox: CheckBox = view.findViewById(R.id.checkBox)

        val mItem: NewDataModel = objects[position]
        imageView.setImageDrawable(context.resources.getDrawable(mItem.img))
        txtTitle.setText(mItem.title)
        txtDescription.setText(mItem.description)
        btnRemove.tag=position

        btnRemove.setOnClickListener {
            val itemSelected = objects.get(btnRemove.tag as Int)
            objects.remove(itemSelected)
            notifyDataSetChanged()
        }


        checkBox.setOnCheckedChangeListener { _, b ->
            var selectedItemTitle = objects[position].title
            if (b){
                objects[position].checked = true
                Toast.makeText(context,"${selectedItemTitle} is selected",Toast.LENGTH_SHORT).show()
            }else{
                objects[position].checked = false
                Toast.makeText(context,"${selectedItemTitle} is unchecked",Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }
}