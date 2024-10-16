/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: data models related to signin, login, user profile creation.
 *****/
package com.example.ecommerceapp.models

// User data model
data class User(
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
    val role: String = "customer"
)

// Login request model (for sending login details)
data class LoginRequest(
    val email: String,
    val password: String
)

// Login response model (for receiving login response from the server)
data class LoginResponse(
    val success: Boolean,
    val data: UserData?, // Nullable in case of login failure
    val message: String?,
    val error: String?
)

// Nested class to represent the data inside the login response
data class UserData(
    val user: UserDetail,
    val token: String
)

// Detailed user info inside the response
data class UserDetail(
    val id: String,
    val name: String,
    val email: String,
    val role: String
)

// Profile response data model
data class ProfileResponse(
    val success: Boolean,
    val data: UserDetail,
    val message: String?
)

data class UpdateProfileRequest(
    val email: String,
    val name: String
)



data class UpdateProfileResponse(
    val success: Boolean,
    val message: String?,
    val error: String?
)

data class DeactivateResponse(
    val success: Boolean,
    val data: UserDetail?, // Optional, based on your response structure
    val message: String?,
    val error: String?,
    val errorCode: String?,
    val errorData: Any?
)

