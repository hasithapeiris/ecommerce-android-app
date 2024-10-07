package com.example.ecommerceapp.services

import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.OrderModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService {
    private val api = RetrofitInstance.orderApi

    fun placeOrder(order: OrderModel, callback: (Boolean, String?) -> Unit) {
        api.placeOrder(order).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    callback(true, "Order placed successfully.")
                } else {
                    callback(false, "Error ${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback(false, t.localizedMessage ?: "Network error. Please try again.")
            }
        })
    }
}