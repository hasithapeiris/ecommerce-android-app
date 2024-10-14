// Updated NotificationApi
package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.NotificationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface NotificationsApi {
    @GET("/api/notifications/my")
    fun getNotifications(@Header("Authorization") token: String): Call<NotificationResponse>

    @POST("/api/notifications/mark-as-read/{id}")
    fun markAsRead(
        @Path("id") notificationId: String,
        @Header("Authorization") token: String
    ): Call<Void>
}
