package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.OrderDetailsAdapter
import com.example.ecommerceapp.databinding.FragmentOrderDetailsBinding
import com.example.ecommerceapp.models.OrderItem
import com.example.ecommerceapp.services.CartService
import com.example.ecommerceapp.services.OrderService

class OrderDetailsFragment : Fragment(R.layout.fragment_order_details) {

    private lateinit var orderDetailsAdapter: OrderDetailsAdapter
    private lateinit var rvCartItems: RecyclerView
    private var token: String? = null
    private val orderService = OrderService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve token from SharedPreferences or arguments
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order_details, container, false)

        val orderId = arguments?.getString("orderId")

        // Initialize RecyclerView
        rvCartItems = view.findViewById(R.id.rvCartItems)

        // Set up the RecyclerView
        rvCartItems.layoutManager = LinearLayoutManager(context)

        // Load your order items (replace with actual data fetching)
        if (token != null && orderId != null) {
            fetchOrderDetails(token!!, orderId)
        }

        return view
    }

    private fun fetchOrderDetails(token: String, orderId: String) {
        orderService.getOrderDetails(token, orderId) { orderDetails, error ->
            if (orderDetails != null) {
                val orderItems = orderDetails.items.map { itemModel ->
                    OrderItem(
                        productName = "Product: ${itemModel.productId}",
                        productPrice = itemModel.unitPrice,
                        quantity = itemModel.quantity,
                        productImage = "Image URL"
                    )
                }

                orderDetailsAdapter = OrderDetailsAdapter(orderItems)
                rvCartItems.adapter = orderDetailsAdapter
            } else {
                Toast.makeText(requireContext(), "Error: $error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}