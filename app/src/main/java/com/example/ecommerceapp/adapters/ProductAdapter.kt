/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Product Adapter to connect with product details fragment.
 * Tutorial: https://www.geeksforgeeks.org/simpleadapter-in-android-with-example/
 * *****/

package com.example.ecommerceapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.DisplayItemBinding
import com.example.ecommerceapp.models.ProductModel

class ProductAdapter(
    private val context: Context,
    private val list: List<ProductModel>,
    private val productClickInterface: ItemOnClickInterface,
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: DisplayItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DisplayItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.tvNameDisplayItem.text = currentItem.name
        holder.binding.tvPriceDisplayItem.text = "Rs.${currentItem.price}"

        // Display the first image from the photos array
        Glide.with(context)
            .load(currentItem.photos.firstOrNull())
            .into(holder.binding.ivDisplayItem)

        holder.itemView.setOnClickListener {
            productClickInterface.onClickItem(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ItemOnClickInterface {
    fun onClickItem(item: ProductModel)
}