package com.example.recyclerview.UNIT_4

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Db_Sql_Demo(context: Context, factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME
                + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "
                + NAME_COL + " TEXT,"
                + AGE_COL + " TEXT"
                + ")"
                )
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(name: String, age: String){
        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(AGE_COL,age)

        val db = this.readableDatabase
        db.insert(TABLE_NAME, null,values)
        db.close()
    }

    fun updateData(name: String, age: String){
        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(AGE_COL,age)

        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "WHERE " + AGE_COL +" > 60", null)
        db.close()
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "WHERE " + AGE_COL + " > 60", null)
        db.close()
    }
    fun getData(): Cursor?{
        val db = this.readableDatabase
        //return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + AGE_COL + " > 25", null)
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }
    //companion object: all the properties mentioned here are linked to class itself rather than a single instance of the class
    companion object{
        private val DATABASE_NAME = "My_DB"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "My_Table1"
        val ID_COL = "id"
        val NAME_COL = "name"
        val AGE_COL = "age"
    }
}