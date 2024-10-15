/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Product service for handling product related APIs.
 * *****/

package com.example.ecommerceapp.services

import com.example.ecommerceapp.api.ProductApi
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.ProductModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductService {

    private val productApi: ProductApi = RetrofitInstance.instance.create(ProductApi::class.java)

    // Get all categories
    fun getCategories(callback: (List<String>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = productApi.getCategories()
                if (response.isSuccessful) {
                    val categories = response.body()?.data ?: emptyList()
                    withContext(Dispatchers.Main) {
                        callback(categories)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback(emptyList())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callback(emptyList())
                }
            }
        }
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
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    // Get products by category
    fun getProductsByCategory(category: String, callback: (List<ProductModel>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = productApi.getProducts()
                if (response.isSuccessful) {
                    val products = response.body()?.data ?: emptyList()
                    val filteredProducts = products.filter { it.category == category }
                    withContext(Dispatchers.Main) {
                        callback(filteredProducts)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}