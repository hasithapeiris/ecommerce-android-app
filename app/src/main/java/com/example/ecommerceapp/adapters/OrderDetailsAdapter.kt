package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.models.OrderItem

class OrderDetailsAdapter(private val orderItems: List<OrderItem>) :
    RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder>() {

    // ViewHolder class for binding the data to the views
    class OrderDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvCartProductName)
        val productPrice: TextView = itemView.findViewById(R.id.tvCartProductPrice)
        val productImage: ImageView = itemView.findViewById(R.id.ivCartProduct)
        val itemCount: TextView = itemView.findViewById(R.id.tvCartItemCount)
        val addButton: Button = itemView.findViewById(R.id.btnCartItemAdd)
        val minusButton: Button = itemView.findViewById(R.id.btnCartItemMinus)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_details_item, parent, false)
        return OrderDetailsViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: OrderDetailsViewHolder, position: Int) {
        val item = orderItems[position]

        // Bind the order item details to the respective views
        holder.productName.text = item.productName
        holder.productPrice.text = item.productPrice.toString()
        holder.itemCount.text = item.quantity.toString()

        // Set the product image using a placeholder for now (you can use libraries like Glide or Picasso)
        holder.productImage.setImageResource(R.drawable.phone1)

        // Handle adding or reducing quantity
        holder.addButton.setOnClickListener {
            // Increase the item quantity (Handle this as per your requirements)
        }

        holder.minusButton.setOnClickListener {
            // Decrease the item quantity (Handle this as per your requirements)
        }
    }

    // Return the size of the dataset (invoked by the layout manager)
    override fun getItemCount() = orderItems.size
}