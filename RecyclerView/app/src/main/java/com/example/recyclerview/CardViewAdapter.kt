package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CardViewAdapter(var context: Context?, var list: ArrayList<CardViewDataModel>): RecyclerView.Adapter<CardViewAdapter.DataHolder>(){
    class DataHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView
        var contactName: TextView
        var contact: TextView
        var fabCall: FloatingActionButton

        init{
            image = itemView.findViewById(R.id.imgViewCard)
            contactName = itemView.findViewById(R.id.txtContactName)
            contact = itemView.findViewById(R.id.txtContact)
            fabCall = itemView.findViewById(R.id.fabCall)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.cardview_demo,parent,false)
        return DataHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.contactName.text = list[position].contactName
        holder.contact.text = list[position].contact
        holder.image.setImageResource(list[position].img)
        holder.fabCall.setOnClickListener {
            Toast.makeText(context,"Calling " + list[position].contactName + " ...", Toast.LENGTH_SHORT).show()
        }
    }
}