/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Order API for handling place orders and fetch orders
 *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.CartResponseWrapper
import com.example.ecommerceapp.models.OrderDetailResponseWrapper
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.models.OrderResponseWrapper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @GET("/api/auth/orders/history")
    fun getOrderItems(@Header("Authorization") token: String): Call<OrderResponseWrapper>

    @GET("/api/auth/orders/track/{orderId}")
    fun getOrderDetails(
        @Header("Authorization") token: String,
        @Path("orderId") orderId: String
    ): Call<OrderDetailResponseWrapper>
}