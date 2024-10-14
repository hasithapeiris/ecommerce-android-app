/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Product API for handling products
 * *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ProductModel
import com.example.ecommerceapp.models.ProductResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("/api/products")
    fun getCategories(): Call<List<String>>

    @GET("/api/products")
    suspend fun getProducts(): Response<ProductResponse>

    @GET("api/vendor/products/{categoryId}")
    fun getProductsByCategory(@Query("category") category: String): Call<List<ProductModel>>
}