/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Order Model.
 *****/

package com.example.ecommerceapp.models

data class OrderResponseWrapper(
    val success: Boolean,
    val data: List<OrderModel>,
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: Any?
)

data class OrderModel(
    val id: String,
    val userId: String,
    val vendorInfo: String?,
    val orderNumber: String,
    val orderDate: String,
    val totalAmount: Double,
    val shippingAddress: String,
    val status: String,
    val items: List<OrderItemModel>,
    val shippingFee: Double,
    val paymentStatus: String,
    val note: String?
)

data class OrderItemModel(
    val productId: String,
    val vendorId: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double,
    val status: String
)

data class OrderItem(
    val productName: String,
    val productPrice: Double,
    val quantity: Int,
    val productImage: String
)
