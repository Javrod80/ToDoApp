package com.example.todoapp.data

import com.example.todoapp.utils.DatabaseManagerCat

class Category(
    var id: Int,
    val category: String = "Categoria",
    val trabajo: String = "Trabajo",
    val personal: String = "Personal",
    val hogar: String = "Hogar",
    val estudios: String = "Estudios",

) {


    companion object {
        const val TABLECAT_NAME = "Categories"
        const val COLUMN_NAME_CATEGORIES = "category"
        const val COLUMN_TRABAJO = "trabajo"
        const val COLUMN_HOGAR = "casa"
        const val COLUMN_ESTUDIO = "estudios"
        const val COLUMN_PERSONAL = "personal"
        val COLUMN_NAMESCAT = arrayOf(
            DatabaseManagerCat.COLUMN_NAMECAT_ID,
            COLUMN_NAME_CATEGORIES,
            COLUMN_TRABAJO,
            COLUMN_HOGAR,
            COLUMN_ESTUDIO,
            COLUMN_PERSONAL

        )




    }

}