package com.example.todoapp.data

import com.example.todoapp.utils.DatabaseManager


class Category(
    var id: Int,
    val category: String
) {


    companion object {
        const val TABLE_NAME = "Categories"
        const val COLUMN_NAME_CATEGORY = "category"
        val COLUMN_NAMESCAT = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_CATEGORY
        )
    }

}