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
    private lateinit var authService: AuthService
    private lateinit var cartService: CartService
    private lateinit var orderService: OrderService
    private lateinit var productService: ProductService

    private lateinit var orderImageUrl: String
    private lateinit var orderName: String
    private var orderSize: String? = null
    private var orderQuantity: Int = 1
    private var orderPrice by Delegates.notNull<Float>()
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
        authService = AuthService()
        orderService = OrderService()
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
        orderImageUrl = item.photos.firstOrNull() ?: ""
        orderName = item.name!!
        orderPrice = item.price!!

        Glide.with(requireContext()).load(orderImageUrl).into(binding.ivDetails)
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
        // Create a CartModel instance with relevant details
        val cartItem = CartModel(
            pid = arguments?.getString("productId"), // Assuming the productId is passed in arguments
            vid = authService.getCurrentUserId(), // Get the current user ID
            imageUrl = null,
            name = "test",
            price = null,
            quantity = orderQuantity
        )

        // Call the addItemToCart method from CartService
        token?.let {
            cartService.addItemToCart(cartItem, it) { success, errorMessage ->
                if (success) {
                    requireActivity().toast("Item added to cart successfully!")
                } else {
                    requireActivity().toast(errorMessage ?: "Failed to add item to cart.")
                }
            }
        }
    }
}
