package com.example.recyclerview.CA2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_1")
data class StudentModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val hostelFee: Float,
    val semFee: Float,
    val messFee: Float
)