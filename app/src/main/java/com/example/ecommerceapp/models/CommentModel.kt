package com.example.ecommerceapp.models

data class ReviewRequest(
    val vendorId: String,
    val customerId: String,
    val comment: String,
    val rating: Int
)

// ReviewResponse.kt


data class ReviewResponse(
    val success: Boolean,
    val data: ReviewData,
    val message: String?,
    val error: String?,
    val errorCode: Int?,
    val errorData: String?
)

data class ReviewData(
    val id: String,
    val vendorId: String,
    val customerId: String,
    val comment: String,
    val rating: Int,
    val createdAt: String,
    val updatedAt: String
)

data class CommentModel(
    val username: String,
    val text: String,
)