package com.example.todoapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp.data.Category
import com.example.todoapp.data.Task


class DatabaseManager(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        const val DATABASE_NAME = "listOfTask.db"
        const val DATABASE_VERSION = 1
        const val COLUMN_NAME_ID = "id"


        private const val SQL_CREATE_TABLE =
            "CREATE TABLE ${Task.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Task.COLUMN_NAME_TASK} TEXT," +
                    "${Task.COLUMN_NAME_DONE} BOOLEAN)"

        private const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS Task"


        const val DATABASECAT_NAME = "listOfCategories"
        const val DATABASECAT_VERSION = 1
        const val COLUMN_NAMECAT_ID = "id"

        private const val SQL_CREATE_TABLECAT =
            "CREATE TABLE ${Category.TABLECAT_NAME} (" +
                    "$COLUMN_NAMECAT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Category.COLUMN_NAME_CATEGORIES} TEXT)"



        private const val SQL_DELETE_TABLECAT = "DROP TABLE IF EXISTS Categories"
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
        db.execSQL(SQL_CREATE_TABLECAT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        drestroyDatabase(db)
        onCreate(db)
    }

    private fun drestroyDatabase(db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE)
        db.execSQL(SQL_DELETE_TABLECAT)


    }


}