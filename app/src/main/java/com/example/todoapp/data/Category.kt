package com.example.todoapp.data

import com.example.todoapp.utils.DatabaseManager

class Category (var id : Int, var category : String)  {




    companion object {
        const val TABLECAT_NAME = "Categories"
        const val COLUMN_NAME_CATEGORIES = "category"
        val  COLUMN_NAMESCAT = arrayOf(
            DatabaseManager.COLUMN_NAMECAT_ID,
            COLUMN_NAME_CATEGORIES,

        )


    }

}