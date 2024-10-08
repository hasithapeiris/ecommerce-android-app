/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Order service for handling order related APIs.
 * *****/

package com.example.ecommerceapp.services

import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.OrderModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService {
    private val api = RetrofitInstance.orderApi

    // Place order by customer
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

    // Get order by user Id
    fun getOrderItemsByUserId(userId: String, callback: (List<OrderModel>?, String?) -> Unit) {
        api.getOrderItemsByUserId(userId).enqueue(object : Callback<List<OrderModel>> {
            override fun onResponse(call: Call<List<OrderModel>>, response: Response<List<OrderModel>>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Error ${response.code()}: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<OrderModel>>, t: Throwable) {
                callback(null, t.localizedMessage ?: "Network error. Please try again.")
            }
        })
    }

}