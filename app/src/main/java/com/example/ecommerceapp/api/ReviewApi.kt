package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ReviewRequest
import com.example.ecommerceapp.models.ReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ReviewApi {
    @POST("api/review")
    fun submitReview(@Body reviewRequest: ReviewRequest): Call<ReviewResponse>
}