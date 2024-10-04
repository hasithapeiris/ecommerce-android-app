package com.example.ecommerceapp.services

import com.example.ecommerceapp.models.ProductModel

class ProductService {
    fun getCategories(callback: (List<String>) -> Unit) {
        // Implement API call to fetch categories (e.g., GET /api/categories)
        // Once the data is fetched, pass it to the callback function
    }

    fun getProducts(callback: (List<ProductModel>) -> Unit) {
        // Implement API call to fetch all items (e.g., GET /api/items)
        callback(
            listOf(
                ProductModel("1", "Brand1", "Product1", "Description1", "imageUrl1", "100"),
                ProductModel("2", "Brand2", "Product2", "Description2", "imageUrl2", "200")
            )
        )
    }

    fun getProductsByCategory(category: String, callback: (List<ProductModel>) -> Unit) {
        // Implement API call to fetch items by category (e.g., GET /api/items?category=<category>)
        callback(
            listOf(
                ProductModel("1", "Brand1", "Product1", "Description1", "imageUrl1", "100")
            )
        )
    }
}