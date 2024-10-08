package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.OrderAdapter
import com.example.ecommerceapp.databinding.FragmentOrdersBinding
import com.example.ecommerceapp.middlewares.Extensions.toast
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.OrderService

class OrdersFragment : Fragment(R.layout.fragment_orders), OrderAdapter.OnLongClickRemove {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var orderList: ArrayList<OrderModel>
    private lateinit var adapter: OrderAdapter

    private lateinit var authService: AuthService
    private lateinit var orderService: OrderService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrdersBinding.bind(view)

        // Initialize services
        authService = AuthService()
        orderService = OrderService()

        binding.orderActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        orderList = ArrayList()

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(context)
        adapter = OrderAdapter(requireContext(), orderList, this)
        binding.rvOrderItems.adapter = adapter
        binding.rvOrderItems.layoutManager = layoutManager

        // Retrieve cart items
        retrieveOrderItems()
    }

    // Retrieve order items using OrderService
    private fun retrieveOrderItems() {
        val userId = authService.getCurrentUserId()

        orderService.getOrderItemsByUserId(userId) { orderItems, errorMessage ->
            if (orderItems != null) {
                orderList.clear()
                orderList.addAll(orderItems)

                adapter.notifyDataSetChanged()
            } else {
                requireActivity().toast(errorMessage ?: "Failed to retrieve ordered items")
            }
        }
    }

    override fun onLongRemove(item: OrderModel, position: Int) {

    }

}