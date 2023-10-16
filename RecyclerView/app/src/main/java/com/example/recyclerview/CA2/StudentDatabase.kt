package com.example.recyclerview.CA2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentModel::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun studentDAO_1(): StudentDAO_1
}