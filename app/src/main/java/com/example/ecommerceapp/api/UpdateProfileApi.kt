/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: retrofitclient interface for profile update.
 *****/
package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.UpdateProfileRequest
import com.example.ecommerceapp.models.UpdateProfileResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UpdateProfileApi {
    //function to handle update profile api request in retrofit level
    @POST("/api/customer/users")
    fun updateProfile(
        @Header("Authorization") token: String,
        @Body updateProfileRequest: UpdateProfileRequest
    ): Call<UpdateProfileResponse>
}
