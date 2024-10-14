// Updated NotificationService
package com.example.ecommerceapp.services

import com.example.ecommerceapp.api.NotificationsApi
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.NotificationData
import com.example.ecommerceapp.models.NotificationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationService {
    private val notificationsApi: NotificationsApi = RetrofitInstance.instance.create(NotificationsApi::class.java)

    fun getNotifications(token: String, callback: (Boolean, List<NotificationData>?) -> Unit) {
        val call = notificationsApi.getNotifications("Bearer $token")
        call.enqueue(object : Callback<NotificationResponse> {
            override fun onResponse(call: Call<NotificationResponse>, response: Response<NotificationResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    callback(true, response.body()?.data)
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<NotificationResponse>, t: Throwable) {
                callback(false, null)
            }
        })
    }

    fun markNotificationAsRead(notificationId: String, token: String, callback: (Boolean) -> Unit) {
        val call = notificationsApi.markAsRead(notificationId, "Bearer $token")
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false)
            }
        })
    }
}
