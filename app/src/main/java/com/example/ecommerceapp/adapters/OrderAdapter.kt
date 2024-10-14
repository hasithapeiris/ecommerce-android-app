/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Order Adapter to connect with order fragment.
 * Tutorial: https://medium.com/edureka/android-adapter-tutorial-68fa7b2e32e7
 * *****/

package com.example.ecommerceapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.databinding.OrderItemBinding
import com.example.ecommerceapp.middlewares.SwipeToDelete
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.models.ProductModel

class OrderAdapter(
    private val context : Context,
    private val list:ArrayList<OrderModel>,
    private val onLongClickRemove: OnLongClickRemove,
    private val orderClickInterface: OrderOnClickInterface,
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

        holder.itemView.setOnClickListener {
            orderClickInterface.onClickItem(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnLongClickRemove{
        fun onLongRemove(item: OrderModel, position: Int)
    }
}

interface OrderOnClickInterface {
    fun onClickItem(item: OrderModel)
}