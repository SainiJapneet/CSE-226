package com.example.recyclerview.UNIT_4.PlacementPortal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDB: RoomDatabase() {
    abstract fun studentDAO(): StudentDAO
}