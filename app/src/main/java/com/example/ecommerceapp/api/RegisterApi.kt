/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: retrofitclient interface for customer registration
 *****/

package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    //function to handle register api request in retrofit level
    @POST("/api/register")
    fun registerUser(@Body user: User): Call<Void>

}