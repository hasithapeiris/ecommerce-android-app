
package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.CommentResponse
import com.example.ecommerceapp.models.ReviewRequest
import com.example.ecommerceapp.models.ReviewResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApi {
    @POST("api/review")
    fun postReview(
        @Header("Authorization") token: String,
        @Body reviewRequest: ReviewRequest
    ): Call<ReviewResponse>

    @GET("api/review/customer/{customerId}")
    fun getComments(
        @Header("Authorization") token: String,
        @Path("customerId") customerId: String
    ): Call<CommentResponse>
}
