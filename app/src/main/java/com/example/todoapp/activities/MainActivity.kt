package com.example.todoapp.activities

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.adapter.CategoriesAdapter
import com.example.todoapp.adapter.TasksAdapter
import com.example.todoapp.data.Category
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.DialogCategoriesBinding
import com.example.todoapp.databinding.DialogTaskBinding
import com.example.todoapp.databinding.ItemCategoriesBinding
import com.example.todoapp.provider.CategoryDAO
import com.example.todoapp.provider.TaskDAO


class MainActivity : AppCompatActivity()
  {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: TasksAdapter
    private var tasklist: List<Task> =
        listOf() // MutableList<Task> se usa para listas que se modifican
    private lateinit var taskDAO: TaskDAO

    private lateinit var adapterCat: CategoriesAdapter
    private var listcategories: List<Category> = listOf()


    private lateinit var categoryDAO: CategoryDAO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        taskDAO = TaskDAO(this)
        categoryDAO = CategoryDAO(this)

        iniListener()

        initRecycledView()
        iniRecycleCat()

        loadData()
        loadCat()


    }

    private fun iniListener() {
        //button add orange
        // button add purple

        binding.floAddButton.setOnClickListener { showDialog() }
        binding.addCatBut.setOnClickListener { createCategory() }

        binding.searchViewTask.setOnQueryTextListener (object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchbytask(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               return false
            }

        }
        )


    }

      private fun searchbytask(query: String) {
          val response = searchbytask(query)

          if (response != null) {
              listcategories= listcategories


          }








      }


      private fun initRecycledView() {

        adapter = TasksAdapter(listOf(), {
            onItemClickListener(it)
        }, {
            onItemClickCheckBoxListener(it)
        }, {
            onItemClickRemoveLister(it)

        })


        binding.recViTask.adapter = adapter
        binding.recViTask.layoutManager = LinearLayoutManager(this)


    }

    private fun iniRecycleCat() {

        adapterCat = CategoriesAdapter(listOf(), {
            onItemCatClickListerer(it)
        }, {
            onCatRemove(it)
        })

        binding.recyCat.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) //recycler horizontal
        binding.recyCat.adapter = adapterCat

    }


    private fun onItemClickCheckBoxListener(position: Int) {
        val task: Task = tasklist[position]
        task.done = !task.done
        taskDAO.update(task)


    }

    private fun createCategory() {

        val categoryBinding = DialogCategoriesBinding.inflate((layoutInflater))
        val create: AlertDialog.Builder = AlertDialog.Builder(this)
        create.setTitle("Categoria")
        create.setPositiveButton("Crear", DialogInterface.OnClickListener { dialog, id ->
            val catName = categoryBinding.dialogEditCat.text.toString()
            val category = Category(-1, catName)
            categoryDAO.insert(category)

            loadCat()
            dialog.dismiss()


        })
        create.setNegativeButton(
            android.R.string.cancel,
            DialogInterface.OnClickListener { dialog, id -> // cancel button
                dialog.dismiss()
            })

        create.setView(categoryBinding.root)

        val alertDialog = create.create()
        alertDialog.show()


    }

    private fun showDialog() {
        //AlertDialog

        /*val spinner: Spinner = findViewById(R.id.spinnerCat)
        var listCat: List<Category> = listOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listCat)*/

        val dialogBinding = DialogTaskBinding.inflate(layoutInflater) //llamar a layout diferente

        val builder: AlertDialog.Builder = AlertDialog.Builder(this) //create dialogo
        builder.setTitle(getString(R.string.new_task)) // titulo del dialogo
        builder.setPositiveButton(
            getString(R.string.create_task),
            DialogInterface.OnClickListener { dialog, id -> // name button
                val taskName =
                    dialogBinding.dialogEditText.text.toString() // funcionalidad del button
                val task = Task(-1, taskName, false) // nueva tarea
                taskDAO.insert(task)

                loadData() // llamar a la nueva tarea creada
                dialog.dismiss()
            })
        builder.setNegativeButton(
            android.R.string.cancel,
            DialogInterface.OnClickListener { dialog, id -> // cancel button
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

    private fun loadCat() {


        listcategories = categoryDAO.findAll()
        adapterCat.updateCategory(listcategories)
        val binding = ItemCategoriesBinding.inflate(layoutInflater)

        binding.tvCategoryName.text


    }


    private fun onItemClickListener(position: Int) {
        val task: Task = tasklist[position]

    }

    private fun onItemCatClickListerer(position: Int) {
        val category: Category = listcategories[position]

    }


    private fun onItemClickRemoveLister(position: Int) {

        val task: Task = tasklist[position]
        //val dialogBinding = ItemTaskBinding.inflate(layoutInflater)

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(getString(R.string.delete_task_title))
        dialogBuilder.setMessage(getString(R.string.delete_task_confirm_message, task.task))
        dialogBuilder.setNegativeButton(android.R.string.cancel) { dialog, _ -> // cancel button
            dialog.dismiss()

        }

        dialogBuilder.setPositiveButton(R.string.delete_task_button) { dialog, _ ->

            taskDAO.delete(task)
            // tasklist.removeAt(position)


            loadData()
            dialog.dismiss()
        }

        //dialogBuilder.setView(dialogBinding.root)


        dialogBuilder.show()


    }


    private fun onCatRemove(position: Int) {

        val category: Category = listcategories[position]
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        dialogBuilder.setTitle(getString(R.string.delete_cat_title))
        dialogBuilder.setMessage(getString(R.string.delete_cat_confirm, category.category))
        dialogBuilder.setNegativeButton(android.R.string.cancel) { dialog, _ -> // cancel button
            dialog.dismiss()

        }

        dialogBuilder.setPositiveButton(R.string.delete_cat_button) { dialog, _ ->

            categoryDAO.delete(category)

            loadCat()
            dialog.dismiss()
        }
        dialogBuilder.show()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}