/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Fragment to handling product details.
 *****/

package com.example.ecommerceapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.ecommerceapp.middlewares.Extensions.toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentDetailsBinding
import com.example.ecommerceapp.models.CartModel
import com.example.ecommerceapp.models.ProductModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.CartService
import com.example.ecommerceapp.services.ProductService
import com.example.ecommerceapp.services.OrderService
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var cartService: CartService
    private lateinit var productService: ProductService

    private lateinit var cartItemImageUrl: String
    private lateinit var cartItemName: String
    private var cartItemPrice by Delegates.notNull<Float>()
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
        binding = FragmentDetailsBinding.bind(view)

        // Initialize services
        productService = ProductService()
        cartService = CartService()

        // Get productId from arguments
        val productId = arguments?.getString("productId") ?: return

        // Handle toolbar back navigation
        binding.detailActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        // Fetch product details by ID
        lifecycleScope.launch {
            val product = productService.getProductById(productId)
            product?.let {
                displayProductDetails(it)
            } ?: run {
                requireActivity().toast("Product not found")
            }
        }

        // Handle add to cart button click
        binding.btnDetailsAddToCart.setOnClickListener {
            addToCart()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun displayProductDetails(item: ProductModel) {
        cartItemImageUrl = item.photos.firstOrNull() ?: ""
        cartItemName = item.name!!
        cartItemPrice = item.price!!

        Glide.with(requireContext()).load(cartItemImageUrl).into(binding.ivDetails)
        binding.tvDetailsItemName.text = item.name
        binding.tvDetailsItemDescription.text = item.description
        binding.tvDetailsItemPrice.text = "Rs.${item.price}"
        binding.tvVendorName.text = "Vendor: Vendor A"
        binding.tvProductCategory.text = "Category: ${item.category}"
        binding.tvCondition.text = "Condition: ${item.condition}"
        binding.tvStatus.text = "Status: ${item.status}"
        binding.tvStock.text = "Stock: ${item.stock} available"
        binding.tvDiscount.text = "Discount: ${item.discount}%"
        binding.tvShippingFee.text = "Shipping Fee: Rs.${item.shippingFee}"
        binding.tvDimensions.text = "Dimensions: ${item.width} x ${item.height} x ${item.length}"
        binding.tvWeight.text = "Weight: ${item.productWeight}"
    }

    // Add to Cart method
    private fun addToCart() {

    }
}
