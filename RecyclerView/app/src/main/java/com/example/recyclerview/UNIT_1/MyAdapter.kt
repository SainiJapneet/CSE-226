package com.example.recyclerview.UNIT_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class MyAdapter(var list:List<MyDataModel>): RecyclerView.Adapter<MyAdapter.DataHolder>() {

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitle: TextView= itemView.findViewById(R.id.txtTitle)
        var txtGenre: TextView = itemView.findViewById(R.id.txtGenre)
        var txtYear: TextView= itemView.findViewById(R.id.txtYear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        //It will inflate the custom layout files
        val inflater = LayoutInflater.from(parent.context).
                inflate(R.layout.recycler_demo_layout,parent,false)
        return DataHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = list[position]
        holder.txtTitle.text = data.getTitle()
        holder.txtGenre.text = data.getGenre()
        holder.txtYear.text = data.getYear()
    }

}