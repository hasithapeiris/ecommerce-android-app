/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Cart API for handling cart features
 *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.CartModel
import com.example.ecommerceapp.models.CartResponseWrapper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface CartApi {
    @GET("/api/auth/cart")
    fun getCartItems(@Header("Authorization") token: String): Call<CartResponseWrapper>

    @POST("/api/auth/cart/add")
    fun addCartItem(@Body cartModel: CartModel, @Header("Authorization") token: String): Call<ResponseBody>

    @POST("/api/auth/cart/purchase")
    fun purchaseOrder(@Body requestBody: Map<String, String>, @Header("Authorization") token: String): Call<ResponseBody>
}