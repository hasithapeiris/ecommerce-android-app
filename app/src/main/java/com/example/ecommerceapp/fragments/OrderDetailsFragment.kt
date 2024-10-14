package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.OrderDetailsAdapter
import com.example.ecommerceapp.databinding.FragmentOrderDetailsBinding
import com.example.ecommerceapp.models.OrderItem
import com.example.ecommerceapp.services.OrderService

class OrderDetailsFragment : Fragment(R.layout.fragment_order_details) {

    private lateinit var orderDetailsAdapter: OrderDetailsAdapter
    private lateinit var rvCartItems: RecyclerView

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
        val orderItems = listOf(
            OrderItem("Nike Predator Gladiator", 1000.0, 1, "url/to/image1"),
            OrderItem("Adidas Shoes", 1200.0, 2, "url/to/image2")
            // Add more items as needed
        )

        // Initialize and set adapter
        orderDetailsAdapter = OrderDetailsAdapter(orderItems)
        rvCartItems.adapter = orderDetailsAdapter

        return view
    }
}