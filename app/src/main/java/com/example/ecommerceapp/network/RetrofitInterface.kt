package com.example.ecommerceapp.network

import com.example.ecommerceapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitInterface {
    @POST("/api/register")
    fun registerUser(@Body user: User): Call<Void>

    // Add other endpoints here as needed (e.g., login, product listing, etc.)
}