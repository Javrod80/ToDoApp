package com.example.todoapp.provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.todoapp.data.Category
import com.example.todoapp.utils.DatabaseManager

class CategoryDAO (context: Context) {

    private var databaseManager : DatabaseManager = DatabaseManager(context)



    fun insert(category: Category): Category {

        val db = databaseManager.writableDatabase

        var values = ContentValues()
        values.put(Category.COLUMN_NAME_CATEGORIES,category.category)



        var newRowId = db.insert(Category.TABLECAT_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        category.id = newRowId.toInt()
        return category



    }

    fun update (category: Category){
        val db = databaseManager.writableDatabase

        var values = ContentValues()

        values.put(Category.COLUMN_NAME_CATEGORIES, category.category)

        var updatedRows = db.update(Category.TABLECAT_NAME, values, "id= ${category.id}",null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()

    }
    fun delete (category: Category){
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Category.COLUMN_NAME_CATEGORIES,"${DatabaseManager.COLUMN_NAMECAT_ID} = ${category.id}",null)
        Log.i ("DATABASE","Deleted rows : $deletedRows")

        db.close()

    }
    @SuppressLint("Range")
    fun find (id: Int): Category? {

        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Category.TABLECAT_NAME,
            Category.COLUMN_NAMESCAT,
            "${DatabaseManager.COLUMN_NAMECAT_ID} = $id",
            null,
            null,
            null,
            null
        )

        var category: Category? = null


        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAMECAT_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORIES))

            // Log.i("DATABASE", "$id -> Task: $task, Done: $done")

            category = Category (id,taskName)


        }
        cursor.close()
        db.close()

        return category !!

    }

    @SuppressLint("Range")
    fun findAll (): List<Category> {
        val db = databaseManager.writableDatabase
        val cursor = db.query(
            Category.TABLECAT_NAME,
            Category.COLUMN_NAMESCAT,
            null,
            null,
            null,
            null,
            null
        )

        var list : MutableList<Category> = mutableListOf()


        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAMECAT_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORIES))

            //Log.i("DATABASE", "$id -> Task: $taskName, Done: $done")

            val category : Category = Category(id, taskName)

            list.add(category)

        }
        cursor.close()
        db.close()

        return list








    }













































}