// ReviewRequest.kt
package com.example.ecommerceapp.models



data class CommentModel(
    val username: String,
    val text: String,
    val rating: Int
)


data class ReviewRequest(
    val vendorId: String,
    val customerId: String,
    val comment: String,
    val rating: Int
)

data class ReviewResponse(
    val success: Boolean,
    val data: ReviewData?,
    val message: String?,
    val error: String?
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

data class CommentResponse(
    val success: Boolean,
    val data: List<CommentItem>,
    val message: String?
)

data class CommentItem(
    val id: String,
    val vendorId: String,
    val customerId: String,
    val comment: String,
    val rating: Int,
    val createdAt: String,
    val updatedAt: String
)
