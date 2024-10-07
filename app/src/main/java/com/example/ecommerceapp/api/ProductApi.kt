package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ProductModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("api/categories")
    fun getCategories(): Call<List<String>>

    @GET("api/vendor/products")
    fun getProducts(): Call<List<ProductModel>>

    @GET("items")
    fun getProductsByCategory(@Query("category") category: String): Call<List<ProductModel>>
}