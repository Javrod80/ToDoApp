package com.example.todoapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TasksAdapter
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TasksAdapter
    private var tasklist :List<Task> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycledView()


    }

    private fun initRecycledView() {

        adapter = TasksAdapter(tasklist){
            onItemClickListener(it)
        }
        binding.recViTask.adapter = adapter
        binding.recViTask.layoutManager = LinearLayoutManager(this, )

    }


    private fun onItemClickListener (position:Int){
      //  tasklist[position].isSelected = !tasklist[position]isSelected





    }
}