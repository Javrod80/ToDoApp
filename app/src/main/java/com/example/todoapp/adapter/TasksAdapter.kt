package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.Task
import com.example.todoapp.databinding.ItemTaskBinding

class TasksAdapter(
    private var task: List<Task> = listOf(),
    val onClickListener: (position: Int) -> Unit,
    //private val onCheckedListener: (position:Int) -> Unit,
    val onRemoveListener: (position: Int) -> Unit

) :
    RecyclerView.Adapter<TasksAdapter.TaskDAOViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskDAOViewHolder {
        val binding = ItemTaskBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        return TaskDAOViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return task.size
    }

    override fun onBindViewHolder(holder: TaskDAOViewHolder, position: Int) {
        holder.render(task[position])
        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
        /*holder.binding.mcheckbox.setOnCheckedChangeListener { _, isChecked ->
           onCheckedListener(position)
        }*/
        holder.binding.trash.setOnClickListener { onRemoveListener(position) }


    }

    fun updateTask(results: List<Task>?) {
        task = results !!
        notifyDataSetChanged()
    }


    class TaskDAOViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(task: Task) {

            binding.tvTask.setText(task.task)
            binding.mcheckbox.isChecked = task.done


        }
    }


}