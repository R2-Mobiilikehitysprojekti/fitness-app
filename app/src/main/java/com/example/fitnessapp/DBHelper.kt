package com.example.fitnessapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf
import com.example.fitnessapp.fragments.FoodFragment
import java.text.SimpleDateFormat
import java.util.*
import java.util.prefs.PreferencesFactory

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    val query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME+ " (" + ENTRY_ID + " INTEGER PRIMARY KEY, " +
            AMOUNT + " INT, " + DATE + " CHAR(255), " + WEEK + " CHAR(255))"
    val query2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2+ " (" + ENTRY_ID2 + " INTEGER PRIMARY KEY, " +
            AMOUNT2 + " INT, " + TYPE + " CHAR(255), " + DATE + " CHAR(255), " + WEEK + " CHAR(255))"



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(query2);
        db.execSQL(query);
        println(query)
        println(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        db?.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME2 + "'");
        onCreate(db!!)
    }
    val sdf = SimpleDateFormat("dd/M/yyyy")
    val today = sdf.format(Date()).toString()
    val weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
    fun addCal(amount: Int){
        val values = ContentValues()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val today = sdf.format(Date()).toString()
        val weekNumber = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR).toString()
        values.put(AMOUNT, amount)

        values.put(DATE, today)

        values.put(WEEK, weekNumber)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun addExer(amount2: Int, type: String){
        val values = ContentValues()

        values.put(AMOUNT2, amount2)

        values.put(TYPE, type)

        values.put(DATE, today)

        values.put(WEEK, weekNumber)

        val db2 = this.writableDatabase

        db2.insert(TABLE_NAME2, null, values)

        db2.close()
    }
// asdfahf
    fun getCal(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT sum(amount) AS "+ TOTAL + " FROM " + TABLE_NAME +" WHERE " + DATE +"="+ "'"+ today +"'" , null)

    }

    fun getExer(): Cursor? {

       val db2 = this.readableDatabase

        return db2.rawQuery("SELECT sum(amount2) AS "+ TOTAL2 + " FROM " + TABLE_NAME2 +" WHERE " + DATE +"="+ "'"+ today +"'" , null)

    }




    companion object {

        private val DATABASE_NAME = "FitnessDB"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "food_table"

        val ENTRY_ID = "id"

        val AMOUNT = "amount"

        val DATE = "date"

        val WEEK = "week"

        val TABLE_NAME2 = "exercise_table"

        val ENTRY_ID2 = "id2"

        val AMOUNT2 = "amount2"

        val TOTAL = "Total"

        val TYPE = "type"

        val TOTAL2 = "total2"


    }

}


