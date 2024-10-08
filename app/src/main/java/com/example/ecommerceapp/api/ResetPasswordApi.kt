package com.example.ecommerceapp.api


import com.example.ecommerceapp.models.ResetPasswordRequest
import com.example.ecommerceapp.models.ResetPasswordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ResetPasswordApi {
    @POST("/api/reset-password")
    fun resetPassword(
        @Header("Authorization") authorization: String,
        @Body request: ResetPasswordRequest
    ): Call<ResetPasswordResponse>
}