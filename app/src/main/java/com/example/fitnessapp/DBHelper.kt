package com.example.fitnessapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.fitnessapp.fragments.FoodFragment
import java.util.prefs.PreferencesFactory

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


    val query = (("CREATE TABLE " + TABLE_NAME)+ " (" + ENTRY_ID + " INTEGER PRIMARY KEY, " +
            AMOUNT + " INT)")
    val query2 =(("CREATE TABLE " + TABLE_NAME2)+ " (" + ENTRY_ID2 + " INTEGER PRIMARY KEY, " +
            AMOUNT2 + " INT)")

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(query2);
        db.execSQL(query);
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        db?.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME2 + "'");
        onCreate(db!!)
    }

    fun addCal(amount: Int){
        val values = ContentValues()

        values.put(AMOUNT, amount)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun addExer(amount: Int){
        val values = ContentValues()

        values.put(AMOUNT2, amount)

        val db2 = this.writableDatabase

        db2.insert(TABLE_NAME2, null, values)

        db2.close()
    }

    fun getCal(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    fun getExer(): Cursor? {

       val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null)

    }



    companion object {

        private val DATABASE_NAME = "FitnessDB"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "food_table"

        val ENTRY_ID = "id"

        val AMOUNT = "amount"

        val TABLE_NAME2 = "exercise_table"

        val ENTRY_ID2 = "id2"

        val AMOUNT2 = "amount2"

    }

}


