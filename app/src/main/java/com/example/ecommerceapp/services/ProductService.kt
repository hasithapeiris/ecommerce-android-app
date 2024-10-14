/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Product service for handling product related APIs.
 * *****/

package com.example.ecommerceapp.services

import android.util.Log
import com.example.ecommerceapp.api.ProductApi
import com.example.ecommerceapp.api.RegisterApi
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.ProductModel
import com.example.ecommerceapp.models.ProductResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService {

    private val productApi: ProductApi = RetrofitInstance.instance.create(ProductApi::class.java)

    // Get all categories
    fun getCategories(callback: (List<String>) -> Unit) {

    }

    // Get all products
    suspend fun getProducts(): Result<List<ProductModel>> {
        return try {
            val response = productApi.getProducts()
            if (response.isSuccessful) {
                val products = response.body()?.data ?: emptyList()
                Result.success(products)
            } else {
                Result.failure(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Get product by ID
    suspend fun getProductById(productId: String): ProductModel? {
        return try {
            val response = productApi.getProducts()
            if (response.isSuccessful) {
                response.body()?.data?.find { it.id == productId }
            } else {
                null // Handle error case
            }
        } catch (e: Exception) {
            null // Handle exception
        }
    }

    // Get products by category
    fun getProductsByCategory(category: String, callback: (List<ProductModel>) -> Unit) {

    }
}