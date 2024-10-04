package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.example.ecommerceapp.Extensions.toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.SizeOnClickInterface
import com.example.ecommerceapp.databinding.FragmentDetailsBinding
import com.example.ecommerceapp.models.ProductModel
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.ProductService
import com.example.ecommerceapp.services.OrderService

class DetailsFragment : Fragment(R.layout.fragment_details), SizeOnClickInterface {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var authService: AuthService
    private lateinit var orderService: OrderService
    private lateinit var productService: ProductService

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

        val productId = arguments?.getString("productId") ?: return

        binding.detailActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        // Fetch item details from API
        productService.getProducts { products ->
            val product = products.find { it.productId == productId }
            product?.let {
                displayProductDetails(it)
            }
        }

        // Handle add to cart button click
        binding.btnDetailsAddToCart.setOnClickListener {
            if (orderSize.isNullOrBlank()) {
                requireActivity().toast("Select Size")
            } else {
                val orderedItem = OrderModel(
                    authService.getCurrentUserId(),
                    productId,
                    orderImageUrl,
                    orderName,
                    orderSize,
                    orderQuantity,
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
            }
        }
    }

    private fun displayProductDetails(item: ProductModel) {
        orderImageUrl = item.imageUrl!!
        orderName = item.productName!!
        orderPrice = item.price!!

        Glide.with(requireContext()).load(item.imageUrl).into(binding.ivDetails)
        binding.tvDetailsItemName.text = item.productName
        binding.tvDetailsItemDescription.text = item.description
        binding.tvDetailsItemPrice.text = item.price
    }

    override fun onClickSize(button: Button, position: Int) {
        orderSize = button.text.toString()
        requireActivity().toast("Size ${button.text} Selected")
    }
}
