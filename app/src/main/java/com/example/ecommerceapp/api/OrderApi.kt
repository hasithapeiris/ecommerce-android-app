package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.OrderModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {
    @POST("/api/orders")
    fun placeOrder(@Body order: OrderModel): Call<ResponseBody>
}