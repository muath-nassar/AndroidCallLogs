package com.example.calllogs.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.calllogs.models.Call

class MySqliteDB(context: Context): SQLiteOpenHelper(context.applicationContext, NAME,null, VERSION) {
companion object{
    var NAME = "my_database"
    var VERSION = 1
}

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE table calls ( id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(150), number VARCHAR(15),type VARCHAR(30))")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS calls")
        onCreate(db)
    }
    fun getAllCalls(): ArrayList<Call>{
        val calls = ArrayList<Call>()

        val db = this.writableDatabase
        val sql =
            "SELECT * FROM calls "
        val cursor = db.rawQuery(sql,null);
        if (cursor.count > 0){
            cursor.moveToFirst()
            while (!cursor.isAfterLast){
                val nameIndex = cursor.getColumnIndex("name")
                val numberIndex = cursor.getColumnIndex("number")
                val typeIndex = cursor.getColumnIndex("type")
                val name = cursor.getString(nameIndex)
                val number = cursor.getString(numberIndex)
                val type = cursor.getString(typeIndex)
                calls.add(Call(type, name, number))
                cursor.moveToNext()
            }
            cursor.close()
        }
        return calls
    }
    fun addCall(call : Call): Boolean{
        val cv = ContentValues()
        cv.put("name", call.name)
        cv.put("number", call.number)
        cv.put("type", call.type)

        val db = writableDatabase
        val action = db.insert("calls",null,cv)
        db.close()
        return action > 0

    }
}