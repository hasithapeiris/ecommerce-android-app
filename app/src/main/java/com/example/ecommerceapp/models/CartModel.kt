/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Cart Model.
 *****/

package com.example.ecommerceapp.models

data class CartModel(
    val pid: String? = null,
    val vid: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val price: Float?,
    val size: String? = null,
    val quantity: Int? = null
)

data class CartResponse(
    val productId: String,
    val vendorId: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double
)
