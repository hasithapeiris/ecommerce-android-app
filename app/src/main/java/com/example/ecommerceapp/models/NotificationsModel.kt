package com.example.ecommerceapp.models

data class NotificationResponse(
    val success: Boolean,
    val data: List<NotificationData>,
    val message: String?,
    val error: String?
)

data class NotificationData(
    val id: String,
    val userId: String,
    val title: String,
    val message: String,
    val roles: String?,
    val isRead: Boolean,
    val createdAt: String
)
