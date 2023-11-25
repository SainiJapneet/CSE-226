package com.example.recyclerview.UNIT_1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.R.layout.grid_demo_layout

class GridAdapter(var ctx:Context,var list: ArrayList<GridModel>): RecyclerView.Adapter<GridAdapter.DataHolder>() {
    class DataHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name: TextView
        var image: ImageView
        init{
            name=itemView.findViewById(R.id.txtName)
            image=itemView.findViewById(R.id.imgView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view: View =LayoutInflater.from(ctx).inflate(grid_demo_layout,parent,false)
        return DataHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.name.text = list[position].name
        holder.image.setImageResource(list[position].image)
        holder.itemView.setOnClickListener {
            Toast.makeText(ctx,list[position].name,Toast.LENGTH_SHORT).show()
            holder.itemView.setBackgroundColor(Color.DKGRAY)
        }
    }
}