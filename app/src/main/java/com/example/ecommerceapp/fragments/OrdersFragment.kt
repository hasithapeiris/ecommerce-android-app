/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Fragment to handling orders.
 *****/

package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.OrderAdapter
import com.example.ecommerceapp.adapters.OrderOnClickInterface
import com.example.ecommerceapp.databinding.FragmentOrdersBinding
import com.example.ecommerceapp.middlewares.Extensions.toast
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.CartService
import com.example.ecommerceapp.services.OrderService

class OrdersFragment : Fragment(R.layout.fragment_orders), OrderAdapter.OnLongClickRemove,
    OrderOnClickInterface {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var orderList: ArrayList<OrderModel>
    private lateinit var adapter: OrderAdapter

    private lateinit var authService: AuthService
    private lateinit var orderService: OrderService
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderService = OrderService()
        // Retrieve token from SharedPreferences or arguments
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentOrdersBinding.bind(view)

        // Initialize services
        orderService = OrderService()

        binding.orderActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        orderList = ArrayList()

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(context)
        adapter = OrderAdapter(requireContext(), orderList, this, this)
        binding.rvOrderItems.adapter = adapter
        binding.rvOrderItems.layoutManager = layoutManager

        // Retrieve order items
        retrieveOrderItems()
    }

    private fun retrieveOrderItems() {
        token?.let {
            orderService.getOrderItems(it) { orders, error ->
                if (orders != null) {
                    orderList.clear()
                    orderList.addAll(orders)
                    adapter.notifyDataSetChanged()
                } else {
                    // Handle the error (e.g., show a toast message)
                    Toast.makeText(requireContext(), error ?: "Error fetching orders", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClickItem(item: OrderModel) {
        val direction = OrdersFragmentDirections.actionOrdersFragment2ToOrderDetailsFragment2(item.id)
        Navigation.findNavController(requireView()).navigate(direction)
    }

    override fun onLongRemove(item: OrderModel, position: Int) {
        // Handle long click remove
    }
}