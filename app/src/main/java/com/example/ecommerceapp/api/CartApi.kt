/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Cart API for handling cart features
 *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.CartModel
import com.example.ecommerceapp.models.CartResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CartApi {
    @GET("/api/auth/cart")
    fun getCartItems(@Header("Authorization") token: String): Call<List<CartResponse>>

    @DELETE("/api/cart/{itemId}")
    fun removeCartItem(@Path("itemId") itemId: String): Call<ResponseBody>

    @POST("/api/auth/cart/add")
    fun addCartItem(@Body cartModel: CartModel, @Header("Authorization") token: String): Call<ResponseBody>
}