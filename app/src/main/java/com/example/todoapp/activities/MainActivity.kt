package com.example.todoapp.activities

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.TasksAdapter
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.DialogTaskBinding
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.provider.TaskDAO


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TasksAdapter
    private var tasklist: List<Task> = listOf()

    private lateinit var taskDAO: TaskDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO = TaskDAO(this)

        iniListener()

        initRecycledView()

        loadData()


    }

    private fun iniListener( ) {
        //button add orange

         binding.floAddButton.setOnClickListener { showDialog() }




    }

    private fun initRecycledView() {

       /* adapter = TasksAdapter(tasklist,){
            onItemClickListener(it)
        }*/
        binding.recViTask.adapter = adapter
        binding.recViTask.layoutManager = LinearLayoutManager(this)

    }



    private fun showDialog() {
        //AlertDialog


        val dialogBinding = DialogTaskBinding.inflate(layoutInflater) //llamar a layout diferente
        val builder: AlertDialog.Builder = AlertDialog.Builder(this) //create dialogo
        builder.setTitle("Nueva tarea") // titulo del dialogo
        builder.setPositiveButton("Crear", DialogInterface.OnClickListener { dialog, id -> // name button
            val taskName = dialogBinding.dialogEditText.text.toString() // funcionalidad del button
            val task = Task(-1, taskName, false) // nueva tarea
            taskDAO.insert(task)

            loadData() // llamar a la nueva tarea creada
            dialog.dismiss()
        })
        builder.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, id -> // cancel button
            dialog.dismiss()
        })

        builder.setView(dialogBinding.root) // root de la vista

        val alertDialog = builder.create()
        alertDialog.show()
    }





    private fun loadData() {
        tasklist = taskDAO.findAll()
        adapter.updateTask(tasklist) // nueva tarea en adapter
    }


    private fun onItemClickListener(position: Int) {
        val task: Task = tasklist[position]


    }

    private fun onItemClickRemoveLister(position: Int) {

        val task : Task = tasklist [position]
        val dialogBinding = ItemTaskBinding.inflate(layoutInflater)

        val dialogBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Eliminar tarea")
        //dialogBuilder.setMessage(getString(R.string.delete_task_confirm , task.task))
        dialogBuilder.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, id -> // cancel button
            dialog.dismiss()
        })

        dialogBuilder.setPositiveButton("Eliminar", DialogInterface.OnClickListener { dialog, id ->

            taskDAO.delete(task)


            loadData()
            dialog.dismiss()
        })


        val alertDialog = dialogBuilder.create()
        alertDialog.show()













    }


}