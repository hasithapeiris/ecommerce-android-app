/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: retrofitclient interface for Login
 *****/

package com.example.ecommerceapp.api
import com.example.ecommerceapp.models.LoginRequest
import com.example.ecommerceapp.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    //function to handle login api request in retrofit level
    @POST("/api/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}
