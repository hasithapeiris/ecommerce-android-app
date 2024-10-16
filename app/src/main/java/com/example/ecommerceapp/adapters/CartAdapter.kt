/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Cart Adapter to connect with cart fragment.
 * Tutorial: https://medium.com/edureka/android-adapter-tutorial-68fa7b2e32e7
 * *****/

package com.example.ecommerceapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.middlewares.SwipeToDelete
import com.example.ecommerceapp.databinding.CartItemBinding
import com.example.ecommerceapp.models.CartModel
import com.example.ecommerceapp.models.CartResponse

class CartAdapter(
    private val context : Context,
    private val list:ArrayList<CartResponse>,
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.binding.tvCartProductName.text = currentItem.productId
        holder.binding.tvCartProductPrice.text = "Rs.${currentItem.unitPrice}"
        holder.binding.tvCartItemCount.text = currentItem.quantity.toString()

        var count = holder.binding.tvCartItemCount.text.toString().toInt()

        // Handle increment and decrement
        holder.binding.btnCartItemAdd.setOnClickListener {
            count++
            currentItem.quantity = count
            holder.binding.tvCartItemCount.text = count.toString()
            notifyItemChanged(position)
        }

        holder.binding.btnCartItemMinus.setOnClickListener {
            if (count > 1) {
                count--
                currentItem.quantity = count
                holder.binding.tvCartItemCount.text = count.toString()
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

