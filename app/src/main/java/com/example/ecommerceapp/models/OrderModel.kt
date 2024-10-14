/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Order Model.
 *****/

package com.example.ecommerceapp.models

data class OrderModel(
    val orderId: String,
    val uid: String? = null,
    val itemId: String? = null,
    val imageUrl: String? = null,
    val itemName: String? = null,
    val size: String? = null,
    val quantity: String,
    val price: String? = null,
    val status: String? = null,
)
