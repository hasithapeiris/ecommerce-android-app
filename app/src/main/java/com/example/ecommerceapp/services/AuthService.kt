package com.example.ecommerceapp.services

import com.example.ecommerceapp.models.User
import com.example.ecommerceapp.network.RetrofitClient
import com.example.ecommerceapp.network.RetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private val retrofitInterface: RetrofitInterface = RetrofitClient.instance.create(RetrofitInterface::class.java)

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

        val call = retrofitInterface.registerUser(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, "Registration failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
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
    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {

    }
    fun isUserLoggedIn(): Boolean {
        return true
    }

}
