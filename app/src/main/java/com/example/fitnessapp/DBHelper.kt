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

    override fun onCreate(db: SQLiteDatabase) {
        val query = (("CREATE TABLE " + TABLE_NAME)+ " (" + ENTRY_ID + " INTEGER PRIMARY KEY, " +
                AMOUNT + " INT)")

        db.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addCal(amount: Int){
        val values = ContentValues()

        values.put(AMOUNT, amount)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun getCal(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }



    companion object {

        private val DATABASE_NAME = "FitnessDB"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "food_table"

        val ENTRY_ID = "id"

        val AMOUNT = "amount"

    }

}


