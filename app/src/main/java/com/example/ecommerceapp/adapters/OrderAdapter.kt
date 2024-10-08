package com.example.ecommerceapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.OrderItemBinding
import com.example.ecommerceapp.middlewares.SwipeToDelete
import com.example.ecommerceapp.models.OrderModel

class OrderAdapter(
    private val context : Context,
    private val list:ArrayList<OrderModel>,
    private val onLongClickRemove: OnLongClickRemove
): RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: OrderItemBinding):RecyclerView.ViewHolder(binding.root){
        private val onSwipeDelete = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                list.removeAt(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        return ViewHolder(OrderItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        val currentItem = list[position]

        Glide
            .with(context)
            .load(currentItem.imageUrl)
            .into(holder.binding.ivOrderProduct)

        holder.binding.tvOrderProductName.text = currentItem.itemName
        holder.binding.tvOrderPrice.text = "Rs.${currentItem.price}"
        holder.binding.tvOrderStatus.text = currentItem.status

        holder.itemView.setOnLongClickListener {
            onLongClickRemove.onLongRemove(currentItem , position)
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnLongClickRemove{
        fun onLongRemove(item: OrderModel, position: Int)
    }
}