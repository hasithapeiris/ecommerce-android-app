package com.example.ecommerceapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.middlewares.SwipeToDelete
import com.example.ecommerceapp.databinding.CartItemBinding
import com.example.ecommerceapp.models.CartModel

class CartAdapter(
    private val context : Context,
    private val list:ArrayList<CartModel>,
    private val onLongClickRemove: OnLongClickRemove
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root){
        private val onSwipeDelete = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                list.removeAt(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        Glide
            .with(context)
            .load(currentItem.imageUrl)
            .into(holder.binding.ivCartProduct)

        holder.binding.tvCartProductName.text = currentItem.name
        holder.binding.tvCartProductPrice.text = "Rs.${currentItem.price}"
        holder.binding.tvCartItemCount.text = currentItem.quantity.toString()

        var count = holder.binding.tvCartItemCount.text.toString().toInt()

        holder.itemView.setOnLongClickListener {
            onLongClickRemove.onLongRemove(currentItem , position)
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnLongClickRemove{
        fun onLongRemove(item: CartModel, position: Int)
    }

}

