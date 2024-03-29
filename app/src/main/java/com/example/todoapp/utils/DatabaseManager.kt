package com.example.todoapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp.data.Category
import com.example.todoapp.data.Task


class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {

        const val DATABASE_NAME = "listOfTask.db"
        const val DATABASE_VERSION = 2
        const val COLUMN_NAME_ID = "id"


        private const val SQL_CREATE_TABLE_TASK =
            "CREATE TABLE ${Task.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Task.COLUMN_NAME_TASK} TEXT," +
                    "${Task.COLUMN_NAME_DONE} BOOLEAN)"

        private const val SQL_DELETE_TABLE_TASK = "DROP TABLE IF EXISTS ${Task.TABLE_NAME}"


        private const val SQL_CREATE_TABLE_CATEGORY =
            "CREATE TABLE ${Category.TABLE_NAME} (" +
                    "${DatabaseManager.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Category.COLUMN_NAME_CATEGORY} TEXT)"


        private const val SQL_DELETE_TABLE_CATEGORY = "DROP TABLE IF EXISTS ${Category.TABLE_NAME}"




    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_TASK)
        db.execSQL(SQL_CREATE_TABLE_CATEGORY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase(db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE_TASK)
        db.execSQL(SQL_DELETE_TABLE_CATEGORY)
    }


}