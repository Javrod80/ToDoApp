package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.Category
import com.example.todoapp.databinding.ItemCategoriesBinding

class CategoriesAdapter(
    private var category : List<Category> = listOf(),
    val onClickListener: (position: Int) -> Unit,
    val onRemoveListener: (position: Int) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryDAOViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDAOViewHolder {
        val binding =
            ItemCategoriesBinding.inflate((LayoutInflater.from(parent.context)), parent, false)
        return CategoryDAOViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: CategoryDAOViewHolder, position: Int) {
        holder.render(category[position])
        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
        holder.binding.delete.setOnClickListener { onRemoveListener(position) }




    }

    fun updateCategory(results: List<Category>?) {
        category = results!!
        notifyDataSetChanged()

    }

    class CategoryDAOViewHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(category: Category) {
            binding.tvCategoryName.setText(category.category)




        }


    }

}