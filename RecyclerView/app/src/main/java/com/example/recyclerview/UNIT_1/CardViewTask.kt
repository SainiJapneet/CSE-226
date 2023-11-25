package com.example.recyclerview.UNIT_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class CardViewTask : AppCompatActivity() {

    lateinit var rcyView: RecyclerView
    var arrList = ArrayList<CardViewDataModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view_task)

        rcyView = findViewById(R.id.rcyView)
        val layoutManager = LinearLayoutManager(this)
        rcyView.layoutManager = layoutManager

        arrList.add(CardViewDataModel(R.drawable.profile,"Mark","+14045958879"))
        arrList.add(CardViewDataModel(R.drawable.profile,"Jack","+14045958879"))
        arrList.add(CardViewDataModel(R.drawable.profile,"Daniel","+14045958879"))
        arrList.add(CardViewDataModel(R.drawable.profile,"Albert","+14045958879"))
        arrList.add(CardViewDataModel(R.drawable.profile,"Steve","+14045958879"))
        arrList.add(CardViewDataModel(R.drawable.profile,"Martin","+14045958879"))

        rcyView.adapter = CardViewAdapter(this,arrList)
    }
}