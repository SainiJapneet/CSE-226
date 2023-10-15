package com.example.recyclerview.UNIT_4.PlacementPortal

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student (
    @PrimaryKey(autoGenerate = true)
    val regNo: Long,
    val name: String,
    val cgpa: Float,
    val reappear: Int,
    val spec: String
)