package com.example.recipeapp.users.view

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.recipeapp.models.User

class DatabaseHeLper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?){
        db?.execSQL("CREATE TABLE data (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)")
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertUser(username: String, password: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Range")
    fun readUser(username: String, password: String): User?{
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null)
        val userExists = cursor.moveToFirst()
        val resultUsername = if (userExists) cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)) else null
        val resultPassword = if (userExists) cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)) else null
        var user: User? = null
        if(resultUsername != null && resultPassword != null){
            user = User(username = resultUsername, passwordHash = resultPassword)
        }

        return user

    }
}