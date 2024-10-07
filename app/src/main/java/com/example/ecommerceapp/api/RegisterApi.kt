package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("/api/register")
    fun registerUser(@Body user: User): Call<Void>

}