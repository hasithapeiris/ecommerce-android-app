/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Order service for handling order related APIs.
 * *****/

package com.example.ecommerceapp.services

import com.example.ecommerceapp.api.OrderApi
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.OrderModel
import com.example.ecommerceapp.models.OrderResponseWrapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService {
    private val orderApi: OrderApi = RetrofitInstance.instance.create(OrderApi::class.java)

    // Get order items
    fun getOrderItems(token: String, callback: (List<OrderModel>?, String?) -> Unit) {
        val call = orderApi.getOrderItems("Bearer $token")
        call.enqueue(object : Callback<OrderResponseWrapper> {
            override fun onResponse(call: Call<OrderResponseWrapper>, response: Response<OrderResponseWrapper>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    callback(response.body()?.data, null)
                } else {
                    callback(null, "Failed to fetch orders")
                }
            }

            override fun onFailure(call: Call<OrderResponseWrapper>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }

}