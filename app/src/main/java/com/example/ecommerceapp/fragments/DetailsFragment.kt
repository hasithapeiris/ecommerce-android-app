/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Fragment to handle product details.
 *****/

package com.example.ecommerceapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.ecommerceapp.middlewares.Extensions.toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentDetailsBinding
import com.example.ecommerceapp.models.ProductModel
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.ProductService
import com.example.ecommerceapp.services.OrderService
import kotlinx.coroutines.launch

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var authService: AuthService
    private lateinit var orderService: OrderService
    private lateinit var productService: ProductService
    private var product: ProductModel? = null // Change to nullable type

    private lateinit var orderImageUrl: String
    private lateinit var orderName: String
    private var orderSize: String? = null
    private var orderQuantity: Int = 1
    private lateinit var orderPrice: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        // Initialize services
        authService = AuthService()
        orderService = OrderService()
        productService = ProductService()

        // Get productId from arguments
        val productId = arguments?.getString("productId") ?: return

        // Handle toolbar back navigation
        binding.detailActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        // Fetch product details by ID
        lifecycleScope.launch {
            product = productService.getProductById(productId) ?: run {
                requireActivity().toast("Product not found")
                return@launch // Exit if product is not found
            }
            displayProductDetails(product!!)

            // Set button click listener here
            binding.button3.setOnClickListener {
                val vendorId = product?.vendorInfo?.id ?: "670caa19dc1d24f04815a321"
                if (vendorId.isNotEmpty()) {
                    val action = DetailsFragmentDirections.actionDetailsFragmentToCommentFragment(vendorId)
                    Navigation.findNavController(view).navigate(action)
                } else {
                    requireActivity().toast("Vendor ID not available")
                }
            }
        }

        // Handle add to cart button click
        binding.btnDetailsAddToCart.setOnClickListener {
            // Ensure product is initialized before accessing it
            product?.let {
                val orderedItem = OrderModel(
                    authService.getCurrentUserId(),
                    productId,
                    orderImageUrl,
                    orderName,
                    orderSize,
                    orderQuantity.toString(),
                    orderPrice
                )

                // Place order using OrderService
                orderService.placeOrder(orderedItem) { success, errorMessage ->
                    if (success) {
                        requireActivity().toast("Order Successfully Placed")
                        Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_cartFragment2)
                    } else {
                        requireActivity().toast("Order failed: $errorMessage")
                    }
                }
            } ?: requireActivity().toast("Product details not available") // Handle case where product is null
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayProductDetails(item: ProductModel) {
        orderImageUrl = item.photos.firstOrNull() ?: ""
        orderName = item.name // Assuming this is a non-nullable property
        orderPrice = item.price.toString() // Assuming this is a non-nullable property

        Glide.with(requireContext()).load(orderImageUrl).into(binding.ivDetails)
        binding.tvDetailsItemName.text = item.name
        binding.tvDetailsItemDescription.text = item.description
        binding.tvDetailsItemPrice.text = "Rs.${item.price}"
        binding.tvProductCategory.text = "Category: ${item.category}"
        binding.tvCondition.text = "Condition: ${item.condition}"
        binding.tvStatus.text = "Status: ${item.status}"
        binding.tvStock.text = "Stock: ${item.stock} available"
        binding.tvDiscount.text = "Discount: ${item.discount}%"
        binding.tvShippingFee.text = "Shipping Fee: Rs.${item.shippingFee}"
        binding.tvDimensions.text = "Dimensions: ${item.width} x ${item.height} x ${item.length}"
        binding.tvWeight.text = "Weight: ${item.productWeight}"
    }
}
