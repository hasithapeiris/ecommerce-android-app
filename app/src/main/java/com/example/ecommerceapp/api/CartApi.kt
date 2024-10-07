package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.CartModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface CartApi {
    @GET("/api/cart/{userId}")
    fun getCartItemsByUserId(@Path("userId") userId: String): Call<List<CartModel>>

    @DELETE("/api/cart/{itemId}")
    fun removeCartItem(@Path("itemId") itemId: String): Call<ResponseBody>
}