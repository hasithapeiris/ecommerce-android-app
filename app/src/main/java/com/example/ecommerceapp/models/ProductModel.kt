package com.example.ecommerceapp.models

data class ProductModel(
    val productId: String? = null,
    val userId: String? = null,
    val vendorName: String? = null,
    val productName: String? = null,
    val productCategory: String? = null,
    val imageUrl: String? = null,
    val condition: String? = null,
    val status: String? = null,
    val description: String? = null,
    val stock: Int? = null,
    val sku: String? = null,
    val price: String? = null,
    val discount: Float? = null,
    val productWeight: Float? = null,
    val width: Float? = null,
    val height: Float? = null,
    val length: Float? = null,
    val shippingFee: String? = null
)
