package com.example.todoapp.provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.todoapp.data.Category
import com.example.todoapp.utils.DatabaseManager

class CategoryDAO (context: Context) {

    private var databaseManagerCat : DatabaseManager = DatabaseManager(context)



    fun insert(category: Category): Category {

        val db = databaseManagerCat.writableDatabase

        val values = ContentValues()
        values.put(Category.COLUMN_NAME_CATEGORY,category.category)



        val newRowId = db.insert(Category.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        category.id = newRowId.toInt()
        return category



    }

    fun update (category: Category){
        val db = databaseManagerCat.writableDatabase

        var values = ContentValues()

        values.put(Category.COLUMN_NAME_CATEGORY, category.category)

        var updatedRows = db.update(Category.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID}= ${category.id}",null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()

    }
    fun delete (category: Category){
        val db = databaseManagerCat.writableDatabase

        val deletedRows = db.delete(Category.TABLE_NAME,"${DatabaseManager.COLUMN_NAME_ID}= ${category.id}",null)
        Log.i ("DATABASE","Deleted rows : $deletedRows")

        db.close()

    }
    @SuppressLint("Range")
    fun find (id: Int): Category? {

        val db = databaseManagerCat.writableDatabase

        val cursor = db.query (
            Category.TABLE_NAME,
            Category.COLUMN_NAMESCAT,
            "${DatabaseManager.COLUMN_NAME_ID}= $id",
            null,
            null,
            null,
            null
        )

        var category: Category? = null


        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORY))


            // Log.i("DATABASE", "$id -> Task: $task, Done: $done")

            category = Category (id, taskName)


        }
        cursor.close()
        db.close()

        return category

    }

    @SuppressLint("Range")
    fun findAll (): List<Category> {
        val db = databaseManagerCat.writableDatabase

        val cursor = db.query(
            Category.TABLE_NAME,
            Category.COLUMN_NAMESCAT,
            null,
            null,
            null,
            null,
            null
        )

        var list : MutableList<Category> = mutableListOf()


        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORY))



            val category  = Category(id, taskName)

            list.add(category)

        }
        cursor.close()
        db.close()

        return list








    }













































}