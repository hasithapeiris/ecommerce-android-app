package com.example.ecommerceapp.models

data class User(
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirmation: String,
    val role: String = "customer"
)
