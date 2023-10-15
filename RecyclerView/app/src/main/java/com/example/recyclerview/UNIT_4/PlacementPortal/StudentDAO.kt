package com.example.recyclerview.UNIT_4.PlacementPortal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {
    @Insert
    suspend fun insert(student: Student)

    @Update
    suspend fun  update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM student")
    fun getStudent():LiveData<List<Student>>

}