/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: data models related to password handling
 *****/
package com.example.ecommerceapp.models
//dataclass password reset request
data class ResetPasswordRequest(
    val email: String,
    val token: String,
    val newPassword: String
)
//dataclass for reset password response
data class ResetPasswordResponse(
    val success: Boolean,
    val data: Any?,
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: String?
)
//dataclass for forgot password response
data class ForgotPasswordResponse(
    val success: Boolean,
    val data: Any?,
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: String?
)