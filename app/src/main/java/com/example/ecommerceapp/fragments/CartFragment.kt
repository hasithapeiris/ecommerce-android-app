/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Fragment to handling cart features.
 *****/

package com.example.ecommerceapp.fragments

import android.content.Context
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
import com.example.ecommerceapp.models.CartResponse
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.CartService
import com.example.ecommerceapp.services.OrderService

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartList: ArrayList<CartResponse>
    private lateinit var adapter: CartAdapter
    private var subTotalPrice = 0
    private var totalPrice = 240

    private lateinit var cartService: CartService
    private lateinit var orderService: OrderService
    private var token: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartService = CartService()
        // Retrieve token from SharedPreferences or arguments
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCartBinding.bind(view)

        cartService = CartService()
        orderService = OrderService()

        binding.cartActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        cartList = ArrayList()

        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(context)
        adapter = CartAdapter(requireContext(), cartList)
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
        token?.let {
            cartService.getCartItems(it) { cartItems, errorMessage ->
                if (cartItems != null) {
                    cartList.clear()
                    cartList.addAll(cartItems)

                    subTotalPrice = cartList.sumOf { it.unitPrice * it.quantity }.toInt()
                    totalPrice = subTotalPrice

                    binding.tvLastSubTotalprice.text = subTotalPrice.toString()
                    binding.tvLastTotalPrice.text = totalPrice.toString()
                    binding.tvLastSubTotalItems.text = "SubTotal Items(${cartList.size})"
                    adapter.notifyDataSetChanged()
                } else {
                    requireActivity().toast(errorMessage ?: "Failed to retrieve cart items")
                }
            }
        }
    }

}