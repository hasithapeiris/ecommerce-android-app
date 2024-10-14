/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Cart Model.
 *****/

package com.example.ecommerceapp.models

data class CartModel(
    val productId: String,
    val vendorId: String,
//    val productName: String,
//    val imageUrl: String,
    val quantity: Int = 1,
    val unitPrice: Double,
)

data class CartResponse(
    val productId: String,
    val vendorId: String,
    var quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double
)

data class CartResponseWrapper(
    val success: Boolean,
    val data: List<CartResponse>,
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: String?
)
