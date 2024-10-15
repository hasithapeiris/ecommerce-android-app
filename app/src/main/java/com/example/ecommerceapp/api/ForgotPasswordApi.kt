/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: retrofitclient interface for forgetpassword
 *****/
package com.example.ecommerceapp.api

import com.example.ecommerceapp.models.ForgotPasswordResponse
import com.example.ecommerceapp.models.TwoFactorVerifyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ForgotPasswordApi {
    //function to handle forget password api request in retrofit level
    @POST("/api/send-2fa-verify")
    fun forgotPassword(@Body email: Map<String, String>): Call<ForgotPasswordResponse>

    @POST("/api/verify-2fa")
    fun verifyTwoFactor(@Body requestBody: Map<String, String>): Call<TwoFactorVerifyResponse>
}
