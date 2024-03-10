package com.example.todoapp.provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.todoapp.data.Category
import com.example.todoapp.utils.DatabaseManagerCat

class CategoryDAO (context: Context) {

    private var databaseManagerCat : DatabaseManagerCat = DatabaseManagerCat(context)



    fun insert(category: Category): Category {

        val db = databaseManagerCat.writableDatabase

        val values = ContentValues()
        values.put(Category.COLUMN_NAME_CATEGORIES,category.category)
        values.put(Category.COLUMN_ESTUDIO,category.category)
        values.put(Category.COLUMN_TRABAJO,category.category)
        values.put(Category.COLUMN_PERSONAL,category.category)
        values.put(Category.COLUMN_HOGAR,category.category)



        val newRowId = db.insert(Category.TABLECAT_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        category.id = newRowId.toInt()
        return category



    }

    fun update (category: Category){
        val db = databaseManagerCat.writableDatabase

        var values = ContentValues()

        values.put(Category.COLUMN_NAME_CATEGORIES, category.category)
        values.put(Category.COLUMN_ESTUDIO,category.category)
        values.put(Category.COLUMN_TRABAJO,category.category)
        values.put(Category.COLUMN_PERSONAL,category.category)
        values.put(Category.COLUMN_HOGAR,category.category)

        var updatedRows = db.update(Category.TABLECAT_NAME, values, "${DatabaseManagerCat.COLUMN_NAMECAT_ID}= ${category.id}",null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()

    }
    fun delete (category: Category){
        val db = databaseManagerCat.writableDatabase

        val deletedRows = db.delete(Category.TABLECAT_NAME,"${DatabaseManagerCat.COLUMN_NAMECAT_ID}= ${category.id}",null)
        Log.i ("DATABASE","Deleted rows : $deletedRows")

        db.close()

    }
    @SuppressLint("Range")
    fun find (id: Int): Category? {

        val db = databaseManagerCat.writableDatabase

        val cursor = db.query (
            Category.TABLECAT_NAME,
            Category.COLUMN_NAMESCAT,
            "${DatabaseManagerCat.COLUMN_NAMECAT_ID}= $id",
            null,
            null,
            null,
            null
        )

        var category: Category? = null


        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManagerCat.COLUMN_NAMECAT_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORIES))
            val trabajo = cursor.getString(cursor.getColumnIndex(Category.COLUMN_TRABAJO))
            val estudio = cursor.getString(cursor.getColumnIndex(Category.COLUMN_ESTUDIO))
            val hogar = cursor.getString(cursor.getColumnIndex(Category.COLUMN_HOGAR))
            val personal = cursor.getString(cursor.getColumnIndex(Category.COLUMN_PERSONAL))


            // Log.i("DATABASE", "$id -> Task: $task, Done: $done")

            category = Category (id,taskName,trabajo,estudio,hogar,personal)


        }
        cursor.close()
        db.close()

        return category

    }

    @SuppressLint("Range")
    fun findAll (): List<Category> {
        val db = databaseManagerCat.writableDatabase

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
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManagerCat.COLUMN_NAMECAT_ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Category.COLUMN_NAME_CATEGORIES))
            val trabajo = cursor.getString(cursor.getColumnIndex(Category.COLUMN_TRABAJO))
            val estudio = cursor.getString(cursor.getColumnIndex(Category.COLUMN_ESTUDIO))
            val hogar = cursor.getString(cursor.getColumnIndex(Category.COLUMN_HOGAR))
            val personal = cursor.getString(cursor.getColumnIndex(Category.COLUMN_PERSONAL))



            val category  = Category(id, taskName,trabajo,estudio,hogar,personal)

            list.add(category)

        }
        cursor.close()
        db.close()

        return list








    }













































}