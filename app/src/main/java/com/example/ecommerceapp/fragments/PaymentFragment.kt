package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentPaymentBinding
import com.example.ecommerceapp.services.CartService


class PaymentFragment : Fragment(R.layout.fragment_payment) {

    private lateinit var binding: FragmentPaymentBinding
    private lateinit var cartService: CartService
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartService = CartService()
        // Retrieve token from SharedPreferences or arguments
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        cartService = CartService()

        binding.paymentActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        setupUI()

        return binding.root
    }

    // Handle "Make Payment" button click
    private fun setupUI() {
        binding.paymentButton.setOnClickListener {
            val shippingAddress = binding.cardNumber.text.toString()

            if (shippingAddress.isNotEmpty()) {
                token?.let { it1 ->
                    cartService.purchaseOrder(it1, shippingAddress) { success, message ->
                        if (success) {
                            Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show()

                            findNavController().navigate(R.id.action_paymentFragment_to_ordersFragment2)
                        } else {
                            Toast.makeText(requireContext(), message ?: "Purchase failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please enter a shipping address", Toast.LENGTH_SHORT).show()
            }
        }
    }

}