// ForgotPasswordApi.kt
package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ForgotPasswordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordApi {
    @POST("/api/forgot-password")
    fun forgotPassword(@Body email: Map<String, String>): Call<ForgotPasswordResponse>
}
