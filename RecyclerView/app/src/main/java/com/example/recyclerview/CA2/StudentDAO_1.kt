package com.example.recyclerview.CA2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO_1 {
@Insert
suspend fun insert(student: StudentModel)

@Update
suspend fun update(student: StudentModel)

@Delete
suspend fun delete(student: StudentModel)

@Query("SELECT * FROM student_1")
fun getData(): LiveData<List<StudentModel>>
}