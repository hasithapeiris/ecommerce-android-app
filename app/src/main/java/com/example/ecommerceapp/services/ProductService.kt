/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Product service for handling product related APIs.
 * *****/

package com.example.ecommerceapp.services

import android.util.Log
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.ProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {
//    private val api = RetrofitInstance.productApi

    // Get all categories
    fun getCategories(callback: (List<String>) -> Unit) {
//        api.getCategories().enqueue(object : Callback<List<String>> {
//            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { categories ->
//                        callback(categories)
//                    }
//                } else {
//                    Log.e("ProductService", "Error fetching products: $response")
//                }
//            }
//
//            override fun onFailure(call: Call<List<String>>, t: Throwable) {
//                Log.e("ProductService", "Error fetching products: ${t.message}")
//            }
//        })
    }

//    // Get all products
//    fun getProducts(callback: (List<ProductModel>) -> Unit) {
//        api.getProducts().enqueue(object : Callback<List<ProductModel>> {
//            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { products ->
//                        callback(products)
//                    }
//                } else {
//                    Log.e("ProductService", "Error fetching products: $response")
//                }
//            }
//
//            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
//                Log.e("ProductService", "Error fetching products: ${t.message}")
//            }
//        })
//    }

    // Get all products
    fun getProducts(callback: (List<ProductModel>) -> Unit) {
        // Sample product data
        val demoProducts = listOf(
            ProductModel(
                productId = "P001",
                userId = "U001",
                vendorName = "Vendor A",
                productName = "Smartphone X1",
                productCategory = "Electronics",
                imageUrl = "https://example.com/product2.jpg",
                condition = "New",
                status = "Available",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis mattis ullamcorper metus convallis laoreet. Etiam eros sapien, auctor a urna quis, rutrum mollis diam.",
                stock = 50,
                sku = "SKU001",
                price = "150000",
                discount = 10.0f,
                productWeight = 0.3f,
                width = 7.5f,
                height = 15.0f,
                length = 0.8f,
                shippingFee = "15"
            ),
            ProductModel(
                productId = "P002",
                userId = "U002",
                vendorName = "Vendor B",
                productName = "Smartphone X2",
                productCategory = "Electronics",
                imageUrl = "https://example.com/product2.jpg",
                condition = "New",
                status = "Out of Stock",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis mattis ullamcorper metus convallis laoreet. Etiam eros sapien, auctor a urna quis, rutrum mollis diam.",
                stock = 0,
                sku = "SKU002",
                price = "120000",
                discount = 5.0f,
                productWeight = 1.5f,
                width = 35.0f,
                height = 24.0f,
                length = 2.0f,
                shippingFee = "25"
            ),

            )

        // Callback with the demo products
        callback(demoProducts)
    }

    // Get products by category
    fun getProductsByCategory(category: String, callback: (List<ProductModel>) -> Unit) {
//        api.getProductsByCategory(category).enqueue(object : Callback<List<ProductModel>> {
//            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { products ->
//                        callback(products)
//                    }
//                } else {
//                    Log.e("ProductService", "Error fetching products: $response")
//                }
//            }
//
//            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
//                Log.e("ProductService", "Error fetching products: ${t.message}")
//            }
//        })
    }
}