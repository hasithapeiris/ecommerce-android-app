package com.example.ecommerceapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceapp.middlewares.Extensions.toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CartAdapter
import com.example.ecommerceapp.databinding.FragmentCartBinding
import com.example.ecommerceapp.models.CartModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.CartService
import com.example.ecommerceapp.services.OrderService

class CartFragment : Fragment(R.layout.fragment_cart), CartAdapter.OnLongClickRemove {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartList: ArrayList<CartModel>
    private lateinit var adapter: CartAdapter
    private var subTotalPrice = 0
    private var totalPrice = 240

    private lateinit var authService: AuthService
    private lateinit var cartService: CartService
    private lateinit var orderService: OrderService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)

        // Initialize services
        authService = AuthService()
        cartService = CartService()
        orderService = OrderService()

        binding.cartActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        cartList = ArrayList()

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter(requireContext(), cartList, this)
        binding.rvCartItems.adapter = adapter
        binding.rvCartItems.layoutManager = layoutManager

        // Retrieve cart items
        retrieveCartItems()

        // Handle checkout button
        binding.btnCartCheckout.setOnClickListener {
            requireActivity().toast("You've Ordered Products worth $totalPrice\n Your Product will be delivered in next 7 days")
            cartList.clear()
            binding.tvLastSubTotalprice.text = "0"
            binding.tvLastTotalPrice.text = "Min 1 product is Required"
            binding.tvLastTotalPrice.setTextColor(Color.RED)

            adapter.notifyDataSetChanged()
        }
    }

    // Retrieve cart items using CartService
    private fun retrieveCartItems() {
        val userId = authService.getCurrentUserId()

        cartService.getCartItemsByUserId(userId) { cartItems, errorMessage ->
            if (cartItems != null) {
                cartList.clear()
                cartList.addAll(cartItems)

                subTotalPrice = cartItems.sumOf { it.price?.toInt() ?: 0 }
                totalPrice = subTotalPrice

                // Update UI
                binding.tvLastSubTotalprice.text = subTotalPrice.toString()
                binding.tvLastTotalPrice.text = totalPrice.toString()
                binding.tvLastSubTotalItems.text = "SubTotal Items(${cartList.size})"
                adapter.notifyDataSetChanged()
            } else {
                requireActivity().toast(errorMessage ?: "Failed to retrieve cart items")
            }
        }
    }

    // Remove item from cart on long click
    override fun onLongRemove(item: CartModel, position: Int) {
        cartService.removeCartItem(item) { success, errorMessage ->
            if (success) {
                cartList.removeAt(position)
                adapter.notifyItemRemoved(position)
                requireActivity().toast("Removed Successfully!")
            } else {
                requireActivity().toast(errorMessage ?: "Failed to remove item")
            }
        }
    }
}