package com.example.calllogs.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.calllogs.models.Call


class MySqliteDB(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    companion object {
        val NAME = "database"
        val VERSION = 10
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE calls (id INTEGER PRIMARY KEY AUTOINCREMENT,type VARCHAR(50),name VARCHAR(50),number VARCHAR(13))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS calls")
        onCreate(db)
    }

    fun addCall(call: Call): Boolean {
        val cv = ContentValues().apply {
            put("name", call.name)
            put("number", call.number)
            put("type", call.type)
        }
        return writableDatabase.insert("calls", null, cv) > 0
    }

    fun getAllCalls(): ArrayList<Call> {
        val arr = arrayListOf<Call>()
        val cursor = readableDatabase.rawQuery("SELECT * FROM calls", null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val id = cursor.getInt(0)
                val type = cursor.getString(1)
                val name = cursor.getString(2)
                val number = cursor.getString(3)
                arr.add(Call(type, name, number, id))
                cursor.moveToNext()
            }
        }
        cursor.close()
        return arr
    }

    fun deleteCall(id: Int): Boolean {
        return writableDatabase.delete("calls","id = $id",null) > 0
    }


}
