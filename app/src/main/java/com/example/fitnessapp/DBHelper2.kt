package com.example.fitnessapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.time.temporal.TemporalAmount

class DBHelper2(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db2: SQLiteDatabase) {
        val query = (("CREATE TABLE" + TABLE_NAME) +"(" + ENTRY_ID + "INTEGER PRIMARY KEY," +
                AMOUNT + "INT)")

        db2.execSQL(query)
    }

    override fun onUpgrade(db2: SQLiteDatabase,p1: Int, p2:Int){

    }

    fun addCal(amount: Int){
        val values = ContentValues()

        values.put(AMOUNT, amount)

        val db2 = this.writableDatabase
        db2.insert(TABLE_NAME, null,values)
        db2.close()
    }

    fun getCal(): Cursor? {
        val db2 = this.readableDatabase
        return db2.rawQuery("SELECT * FROM" + TABLE_NAME, null)
    }

    companion object{
        private val DATABASE_NAME = "FitnessDB2"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "exercise_table"
        val ENTRY_ID = "id"
        val AMOUNT = "amount"
    }

}