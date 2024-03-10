package com.example.todoapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todoapp.data.Category

class DatabaseManagerCat(context: Context) :
    SQLiteOpenHelper(context, DATABASECAT_NAME, null, DATABASECAT_VERSION) {


    companion object {

        const val DATABASECAT_NAME = "listOfCategories.db"
        const val DATABASECAT_VERSION = 1
        const val COLUMN_NAMECAT_ID = "id"

        private const val SQL_CREATE_TABLECAT =
            "CREATE TABLE ${Category.TABLECAT_NAME} (" +
                    "$COLUMN_NAMECAT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Category.COLUMN_NAME_CATEGORIES} TEXT,"+
                    "${Category.COLUMN_ESTUDIO} TEXT,"+
                    "${Category.COLUMN_TRABAJO} TEXT,"+
                    "${Category.COLUMN_PERSONAL} TEXT,"+
                    "${Category.COLUMN_HOGAR} TEXT)"


        private const val SQL_DELETE_TABLECAT = "DROP TABLE IF EXISTS Category"


    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLECAT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        destroyDatabase(db)

        onCreate(db)


    }

    private fun destroyDatabase(db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLECAT)


    }


}