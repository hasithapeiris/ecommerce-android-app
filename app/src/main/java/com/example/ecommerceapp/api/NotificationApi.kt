package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.NotificationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface NotificationsApi {
    @GET("/api/notifications/my")
    fun getNotifications(@Header("Authorization") token: String): Call<NotificationResponse>
}
