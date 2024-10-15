/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Order service for handling order related APIs.
 * *****/

package com.example.ecommerceapp.services

import com.example.ecommerceapp.api.OrderApi
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.CancelOrderRequest
import com.example.ecommerceapp.models.CancelOrderResponse
import com.example.ecommerceapp.models.OrderDetailResponseWrapper
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

    // Fetch specific order details by orderId
    fun getOrderDetails(token: String, orderId: String, callback: (OrderModel?, String?) -> Unit) {
        val call = orderApi.getOrderDetails("Bearer $token", orderId)
        call.enqueue(object : Callback<OrderDetailResponseWrapper> {
            override fun onResponse(call: Call<OrderDetailResponseWrapper>, response: Response<OrderDetailResponseWrapper>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    callback(response.body()?.data, null)
                } else {
                    callback(null, "Failed to fetch order details")
                }
            }

            override fun onFailure(call: Call<OrderDetailResponseWrapper>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }

    // Cancel Order by orderId
    fun cancelOrder(
        token: String,
        orderId: String,
        reason: String,
        callback: (CancelOrderResponse?, String?) -> Unit
    ) {
        val cancelRequest = CancelOrderRequest(reason)
        val call = orderApi.cancelOrder("Bearer $token", orderId, cancelRequest)
        call.enqueue(object : Callback<CancelOrderResponse> {
            override fun onResponse(call: Call<CancelOrderResponse>, response: Response<CancelOrderResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Failed to submit cancellation request")
                }
            }

            override fun onFailure(call: Call<CancelOrderResponse>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }
}