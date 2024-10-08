package com.example.ecommerceapp.services

import android.util.Log
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.ProductModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {
    private val api = RetrofitInstance.productApi

    // Get all categories
    fun getCategories(callback: (List<String>) -> Unit) {
        api.getCategories().enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    response.body()?.let { categories ->
                        callback(categories)
                    }
                } else {
                    Log.e("ProductService", "Error fetching products: $response")
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("ProductService", "Error fetching products: ${t.message}")
            }
        })
    }

    // Get all products
    fun getProducts(callback: (List<ProductModel>) -> Unit) {
        api.getProducts().enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { products ->
                        callback(products)
                    }
                } else {
                    Log.e("ProductService", "Error fetching products: $response")
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Log.e("ProductService", "Error fetching products: ${t.message}")
            }
        })
    }

    // Get products by category
    fun getProductsByCategory(category: String, callback: (List<ProductModel>) -> Unit) {
        api.getProductsByCategory(category).enqueue(object : Callback<List<ProductModel>> {
            override fun onResponse(call: Call<List<ProductModel>>, response: Response<List<ProductModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let { products ->
                        callback(products)
                    }
                } else {
                    Log.e("ProductService", "Error fetching products: $response")
                }
            }

            override fun onFailure(call: Call<List<ProductModel>>, t: Throwable) {
                Log.e("ProductService", "Error fetching products: ${t.message}")
            }
        })
    }
}