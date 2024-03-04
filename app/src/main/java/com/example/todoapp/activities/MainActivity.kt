package com.example.todoapp.activities

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapter.TasksAdapter
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.provider.TaskDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TasksAdapter
    private var tasklist :List<Task> = listOf()

    private lateinit var taskDAO: TaskDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO = TaskDAO(this)
        binding.floAddButton.isActivated





        taskDAO.insert(Task(-1, "Comprar leche", false))
        taskDAO.insert(Task(-1, "Pagar alquiler", true))

        initRecycledView()

        loadData()

    }

    private fun initRecycledView() {

        adapter = TasksAdapter(tasklist){
            onItemClickListener(it)
        }
        binding.recViTask.adapter = adapter
        binding.recViTask.layoutManager = LinearLayoutManager(this, )

    }

    private fun loadData() {
        tasklist = taskDAO.findAll()
        adapter.updateTask(tasklist)
    }




    private fun onItemClickListener (position:Int){
      val task : Task = tasklist[position]








    }
}