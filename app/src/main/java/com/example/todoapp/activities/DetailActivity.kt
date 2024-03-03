package com.example.todoapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ItemTaskBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ItemTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

    }


    private fun loadData() {

        binding.mcheckbox
        binding.floAddButton

    }











}