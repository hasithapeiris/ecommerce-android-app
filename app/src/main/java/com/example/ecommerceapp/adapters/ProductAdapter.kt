package com.example.ecommerceapp.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
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
    private val likeClickInterface: LikeOnClickInterface,
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
        holder.binding.tvNameDisplayItem.text = "${currentItem.productName}"
        holder.binding.tvPriceDisplayItem.text = "Rs.${currentItem.price}"

        Glide
            .with(context)
            .load(currentItem.imageUrl)
            .into(holder.binding.ivDisplayItem)

        holder.itemView.setOnClickListener {
            productClickInterface.onClickItem(list[position])
        }

        holder.binding.btnLike.setOnClickListener {
            if(holder.binding.btnLike.isChecked){
                holder.binding.btnLike.backgroundTintList = ColorStateList.valueOf(Color.RED)
                likeClickInterface.onClickLike(currentItem)
            }
            else{
                holder.binding.btnLike.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ItemOnClickInterface {
    fun onClickItem(item: ProductModel)
}

interface LikeOnClickInterface {
    fun onClickLike(item :ProductModel)
}