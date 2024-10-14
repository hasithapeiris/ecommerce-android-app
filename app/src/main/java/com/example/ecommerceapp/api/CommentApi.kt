
package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ReviewRequest
import com.example.ecommerceapp.models.ReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CommentApi {
    @POST("api/review")
    fun postReview(
        @Header("Authorization") token: String,
        @Body reviewRequest: ReviewRequest
    ): Call<ReviewResponse>
}
