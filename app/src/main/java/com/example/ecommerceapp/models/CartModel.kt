package com.example.ecommerceapp.models

data class CartModel(
    val pid: String? = null,
    val uid: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val price: String? = null,
    val quantity: Int? = null
)
