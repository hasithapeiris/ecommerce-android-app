package com.example.ecommerceapp.services

import com.example.ecommerceapp.models.ItemModel

class ItemService {
    fun getCategories(callback: (List<String>) -> Unit) {
        // Implement API call to fetch categories (e.g., GET /api/categories)
        // Once the data is fetched, pass it to the callback function
    }

    fun getItems(callback: (List<ItemModel>) -> Unit) {
        // Implement API call to fetch all items (e.g., GET /api/items)
        callback(
            listOf(
                ItemModel("1", "Brand1", "Product1", "Description1", "imageUrl1", "100"),
                ItemModel("2", "Brand2", "Product2", "Description2", "imageUrl2", "200")
            )
        )
    }

    fun getItemsByCategory(category: String, callback: (List<ItemModel>) -> Unit) {
        // Implement API call to fetch items by category (e.g., GET /api/items?category=<category>)
        callback(
            listOf(
                ItemModel("1", "Brand1", "Product1", "Description1", "imageUrl1", "100")
            )
        )
    }
}