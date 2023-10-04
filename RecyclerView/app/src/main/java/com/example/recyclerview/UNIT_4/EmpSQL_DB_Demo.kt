package com.example.recyclerview.UNIT_4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class EmpSQL_DB_Demo(context: Context, factory: SQLiteDatabase.CursorFactory?):SQLiteOpenHelper(context, DATABASE_NAME , factory , DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME
                + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + NAME_COL + " TEXT,"
                + SALARY_COL + " TEXT,"
                + EXPERIENCE_COl + " TEXT"
                + ")"
                )
        db?.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addEmp(id: String?, name: String?,salary: String?, exp: String?){
        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(ID_COL,id)
        values.put(SALARY_COL,salary)
        values.put(EXPERIENCE_COl,exp)

        val db = this.readableDatabase
        db.insert(TABLE_NAME, null,values)
        db.close()
    }

    fun printEmp(): Cursor?{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object{
        private val DATABASE_NAME = "Emp_DB"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "EmployeeTable"
        val ID_COL = "id"
        val NAME_COL = "name"
        val SALARY_COL = "salary"
        val EXPERIENCE_COl = "axp"
    }
}