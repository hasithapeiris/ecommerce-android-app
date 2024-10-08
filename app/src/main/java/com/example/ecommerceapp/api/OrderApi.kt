/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Order API for handling place orders and fetch orders
 *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.OrderModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OrderApi {
    @POST("/api/orders")
    fun placeOrder(@Body order: OrderModel): Call<ResponseBody>

    @GET("/api/orders/{userId}")
    fun getOrderItemsByUserId(@Path("userId") userId: String): Call<List<OrderModel>>
}