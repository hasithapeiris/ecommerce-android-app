package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ProfileResponse
import com.example.ecommerceapp.models.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {

    @GET("/api/own-user")
    fun getUserProfile(@Header("Authorization") token: String): Call<ProfileResponse>
}
