/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description:retrofitclient interface for Profile view
 *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ProfileResponse
import com.example.ecommerceapp.models.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {
    //function to handle user details api request in retrofit level
    @GET("/api/own-user")
    fun getUserProfile(@Header("Authorization") token: String): Call<ProfileResponse>
}
