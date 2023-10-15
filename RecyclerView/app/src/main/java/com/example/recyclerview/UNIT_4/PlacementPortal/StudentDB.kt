package com.example.recyclerview.UNIT_4.PlacementPortal

import androidx.room.Database

@Database(entities = [Student::class], version = 1)
abstract class StudentDB {
    abstract fun studentDAO(): StudentDAO
}