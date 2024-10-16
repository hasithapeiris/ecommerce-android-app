/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Main category adapter.
 * Tutorial: https://www.geeksforgeeks.org/simpleadapter-in-android-with-example/
 * *****/

package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.databinding.CategorymainItemBinding
import com.example.ecommerceapp.models.CategoryModel

class MainCategoryAdapter(
    private val list: ArrayList<CategoryModel>,
    val onClickCategory: CategoryOnClickInterface
): RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: CategorymainItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategorymainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = list[position]
        holder.binding.btnItemCategory.text = category.name

        holder.binding.btnItemCategory.setOnClickListener {
            onClickCategory.onClickCategory(category)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface CategoryOnClickInterface {
    fun onClickCategory(category: CategoryModel)
}
