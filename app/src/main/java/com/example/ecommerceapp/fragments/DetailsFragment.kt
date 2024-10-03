package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.example.ecommerceapp.Extensions.toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.SizeAdapter
import com.example.ecommerceapp.adapters.SizeOnClickInterface
import com.example.ecommerceapp.databinding.FragmentDetailsBinding
import com.example.ecommerceapp.models.OrderModel

class DetailsFragment : Fragment(R.layout.fragment_details), SizeOnClickInterface {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var sizeAdapter: SizeAdapter
    private val args: DetailsFragmentArgs by navArgs()

    // Variables for product and order details
    private lateinit var currentToken: String
    private lateinit var orderImageUrl: String
    private lateinit var orderName: String
    private var orderSize: String? = null
    private var orderQuantity: Int = 1
    private lateinit var orderPrice: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        // Get the authentication token from Shared Preferences (this is set after login)
        val sharedPrefs = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        currentToken = sharedPrefs.getString("auth_token", "") ?: ""

        if (currentToken.isEmpty()) {
            // If token is not present, redirect to the login screen
            Navigation.findNavController(view).navigate(R.id.signInFragment)
        }

        val itemId = args.itemId

        // Toolbar navigation
        binding.detailActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        // Fetch product details from the external API
        fetchItemDetails(itemId)

        // Set up size selector
        val sizeList = listOf("5", "6", "7", "8", "9", "10")
        sizeAdapter = SizeAdapter(requireContext(), sizeList, this)
        binding.rvSelectSize.adapter = sizeAdapter

        // Handle Add to Cart
        binding.btnDetailsAddToCart.setOnClickListener {
            val orderedProduct = OrderModel(
                token = currentToken, // Now use the token to track the user session
                itemId = itemId,
                imageUrl = orderImageUrl,
                itemName = orderName,
                size = orderSize,
                quantity = orderQuantity,
                price = orderPrice
            )

            if (orderSize.isNullOrBlank()) {
                requireActivity().toast("Select Size")
            } else {
                // Call API to add the product to cart
                addToCart(orderedProduct)

                Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_cartFragment2)
            }
        }
    }

    // Function to fetch product details using the external API
    private fun fetchItemDetails(productId: String) {
        // Simulating API call to fetch item details
        // val apiService = RetrofitInstance.create(ApiService::class.java)
        // val call = apiService.getProductDetails("Bearer $currentToken", productId)
        // call.enqueue(object : Callback<Product> {
        //     override fun onResponse(call: Call<Product>, response: Response<Product>) {
        //         val product = response.body()
        //         if (product != null) {
        //             orderImageUrl = product.imageUrl
        //             orderName = product.name
        //             orderPrice = product.price

        //             binding.tvDetailsProductPrice.text = "₹${product.price}"
        //             binding.tvDetailsProductName.text = "${product.brand} ${product.name}"
        //             binding.tvDetailsProductDescription.text = product.description
        //             Glide.with(requireContext()).load(product.imageUrl).into(binding.ivDetails)
        //         }
        //     }
        //     override fun onFailure(call: Call<Product>, t: Throwable) {
        //         requireActivity().toast("Failed to load product details")
        //     }
        // })
    }

    // Function to add item to cart using the external API
    private fun addToCart(orderedProduct: OrderModel) {
        // Simulating API call to add order
        // val apiService = RetrofitInstance.create(ApiService::class.java)
        // val call = apiService.addProductToCart("Bearer $currentToken", orderedProduct)
        // call.enqueue(object : Callback<ApiResponse> {
        //     override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
        //         if (response.isSuccessful) {
        //             requireActivity().toast("Order Successfully Added to Cart")
        //         } else {
        //             requireActivity().toast("Failed to add order to cart")
        //         }
        //     }
        //     override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
        //         requireActivity().toast("Failed to communicate with backend")
        //     }
        // })
    }

    override fun onClickSize(button: Button, position: Int) {
        orderSize = button.text.toString()
        requireActivity().toast("Size ${button.text} Selected")
    }
}
