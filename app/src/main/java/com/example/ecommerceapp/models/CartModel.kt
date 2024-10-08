/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Cart Model.
 *****/

package com.example.ecommerceapp.models

data class CartModel(
    val pid: String? = null,
    val uid: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val price: String? = null,
    val size: String? = null,
    val quantity: Int? = null
)
