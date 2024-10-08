package com.example.ecommerceapp.models

data class ResetPasswordRequest(
    val email: String,
    val token: String,
    val newPassword: String
)

data class ResetPasswordResponse(
    val success: Boolean,
    val data: Any?, // null in this case
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: String?
)

data class ForgotPasswordResponse(
    val success: Boolean,
    val data: Any?,
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: String?
)