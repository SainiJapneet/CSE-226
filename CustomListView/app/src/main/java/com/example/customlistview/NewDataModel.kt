package com.example.customlistview

class NewDataModel(var title:String, var description: String,var img: Int, var checked: Boolean) {
    fun isChecked(): Boolean{
        return checked
    }
}