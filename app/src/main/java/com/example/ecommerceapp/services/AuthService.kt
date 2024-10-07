package com.example.ecommerceapp.services

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.ecommerceapp.api.LoginApi
import com.example.ecommerceapp.api.ProfileApi
import com.example.ecommerceapp.models.User
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.api.RegisterApi
import com.example.ecommerceapp.models.LoginRequest
import com.example.ecommerceapp.models.LoginResponse
import com.example.ecommerceapp.models.ProfileResponse
import com.example.ecommerceapp.models.UserDetail

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthService {
    private val registerApi: RegisterApi = RetrofitInstance.instance.create(RegisterApi::class.java)
    private val loginApi: LoginApi = RetrofitInstance.instance.create(LoginApi::class.java)
    private val profileApi: ProfileApi = RetrofitInstance.instance.create(ProfileApi::class.java)



    /**
     * Create a new user with name, email, password, password confirmation, and role.
     * @param email The user's email address.
     * @param name The user's name.
     * @param password The user's password.
     * @param passwordConfirmation The password confirmation.
     * @param callback A callback function that returns success status and an optional error message.
     */
    fun createUser(email: String, name: String, password: String, passwordConfirmation: String, callback: (Boolean, String?) -> Unit) {
        val user = User(name, email, password, passwordConfirmation)

        val call = registerApi.registerUser(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, "Registration failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                System.out.println(t.message);
                callback(false, "Error: ${t.message}")

            }
        })
    }
    fun getCurrentUserId(): String {
        // Return a temporary hardcoded user ID
        return "temporaryUserId"
    }
    fun signOut() {
        // Implement sign out logic (e.g., clearing session, API call to sign out)
    }
    fun signIn(context: Context, email: String, password: String, callback: (Boolean, String?) -> Unit) {
        val request = LoginRequest(email, password)
        val call = loginApi.loginUser(request)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    // Handle successful login, e.g., save token
                    val token = response.body()?.data?.token

                    // Save the token in SharedPreferences
                    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
                    val editor = sharedPreferences?.edit()
                    editor?.putString("TOKEN", token)
                    editor?.apply()

                    // Callback on success
                    callback(true, null)
                } else {
                    // Handle errors
                    callback(false, response.body()?.message ?: "Login failed")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle failure
                callback(false, "Network error: ${t.message}")
            }
        })
    }
    // Fetch the user's profile using the token from login
    fun getUserProfile(token: String, callback: (Boolean, UserDetail?) -> Unit) {
        val call = profileApi.getUserProfile("Bearer $token")
        call.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    val userDetail = response.body()?.data
                    callback(true, userDetail)
                } else {
                    callback(false, null)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                callback(false, null)
            }
        })
    }
    fun isUserLoggedIn(): Boolean {
        return true
    }

}
