package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentCancelOrderBinding
import com.example.ecommerceapp.databinding.FragmentOrderDetailsBinding
import com.example.ecommerceapp.services.OrderService

class CancelOrderFragment : Fragment() {

    private lateinit var binding: FragmentCancelOrderBinding
    private var token: String? = null
    private val orderService = OrderService()
    private var orderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve token from SharedPreferences or arguments
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null)

        // Get orderId from arguments
        orderId = arguments?.getString("orderId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cancel_order, container, false)

        binding = FragmentCancelOrderBinding.bind(view)

        binding.cancelOrderActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        // Set up the cancel button click listener
        view.findViewById<Button>(R.id.cancel_button).setOnClickListener {
            val reason = view.findViewById<EditText>(R.id.reason).text.toString()

            if (reason.isNotEmpty() && token != null && orderId != null) {
                cancelOrder(orderId!!, reason)
            } else {
                Toast.makeText(requireContext(), "Please provide a reason for cancellation", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun cancelOrder(orderId: String, reason: String) {
        orderService.cancelOrder(token!!, orderId, reason) { response, error ->
            if (response != null && response.success) {
                Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                // Navigate to the orders page upon successful cancellation
                findNavController().navigate(R.id.action_cancelOrderFragment_to_ordersFragment2)
            } else {
                Toast.makeText(requireContext(), error ?: "Error canceling order", Toast.LENGTH_SHORT).show()
            }
        }
    }

}