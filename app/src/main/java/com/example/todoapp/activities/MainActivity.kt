package com.example.todoapp.activities

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.CategoriesAdapter
import com.example.todoapp.adapter.TasksAdapter
import com.example.todoapp.data.Category
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.DialogTaskBinding
import com.example.todoapp.databinding.ItemCategoriesBinding
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.provider.CategoryDAO
import com.example.todoapp.provider.TaskDAO


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TasksAdapter
    private  var tasklist:List<Task> = listOf() // MutableList<Task> se usa para listas que se modifican
    private lateinit var taskDAO: TaskDAO
    private var listcategories : List<Category>  = listOf()

    private lateinit var categoryDAO : CategoryDAO
    private lateinit var adapterCat : CategoriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskDAO = TaskDAO(this)
        categoryDAO = CategoryDAO(this)

        iniListener()

        initRecycledView()
        iniRecycleCat()

        loadData()


    }

    private fun iniListener( ) {
        //button add orange

         binding.floAddButton.setOnClickListener { showDialog() }




    }

    private fun initRecycledView() {  

        adapter = TasksAdapter(listOf(),{
            onItemClickListener(it)
        },{
            onItemClickCheckBoxListener(it)
        },{
            onItemClickRemoveLister(it)

        })


        binding.recViTask.adapter = adapter
        binding.recViTask.layoutManager = LinearLayoutManager(this)


    }

    private fun iniRecycleCat (){

        adapterCat = CategoriesAdapter (listcategories) {
            onItemClickListener(it)
        }
        binding.recyCat.adapter = adapterCat
        binding.recyCat.layoutManager = GridLayoutManager (this,2)
    }




    private fun onItemClickCheckBoxListener(position:Int) {
        val task :Task = tasklist[position]
        task.done = !task.done
        taskDAO.update(task)



    }
    private fun createCategory () {

        val categoryBinding = ItemCategoriesBinding.inflate((layoutInflater))
        val create : AlertDialog.Builder = AlertDialog.Builder(this)
        create.setTitle("Categoria")
        create.setPositiveButton("Nueva Categoria",DialogInterface.OnClickListener{dialog, id ->
            val catName = categoryBinding.tvCategoryName.text.toString()
            val category = Category (-1,catName)
            categoryDAO.insert(category)
        })





    }








    private fun showDialog() {
        //AlertDialog


        val dialogBinding = DialogTaskBinding.inflate(layoutInflater) //llamar a layout diferente
        val builder: AlertDialog.Builder = AlertDialog.Builder(this) //create dialogo
        builder.setTitle(getString(R.string.new_task)) // titulo del dialogo
        builder.setPositiveButton(getString(R.string.create_task), DialogInterface.OnClickListener { dialog, id -> // name button
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
        tasklist = taskDAO.findAll().toMutableList()//.toMutableList() // se llama a mutable
        adapter.updateTask(tasklist) // nueva tarea en adapter
    }


    private fun onItemClickListener(position: Int) {
        val task: Task = tasklist[position]


    }

    private fun onItemClickRemoveLister(position: Int) {

        val task : Task = tasklist [position]
        //val dialogBinding = ItemTaskBinding.inflate(layoutInflater)

        val dialogBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.delete_task_title))
        dialogBuilder.setMessage(getString(R.string.delete_task_confirm_message , task.task))
        dialogBuilder.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialog, id -> // cancel button
            dialog.dismiss()

        })

        dialogBuilder.setPositiveButton(R.string.delete_task_button, DialogInterface.OnClickListener { dialog, id ->

            taskDAO.delete(task)
           // tasklist.removeAt(position)



            loadData()
            dialog.dismiss()
        })

        //dialogBuilder.setView(dialogBinding.root)



        dialogBuilder.show()



    }


}