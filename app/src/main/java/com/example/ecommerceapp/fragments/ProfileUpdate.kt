/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: Fragment view handling for profile update fragment
 *****/

package com.example.ecommerceapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.services.AuthService

class ProfileUpdate : Fragment() {

    private lateinit var authService: AuthService
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_update, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        // Initialize AuthService (assuming it's already setup elsewhere in the app)
        authService = AuthService()

        // Example form submission for updating profile
        val updateButton: Button = view.findViewById(R.id.update_profile_button)
        val emailEditText: EditText = view.findViewById(R.id.email_input)
        val nameEditText: EditText = view.findViewById(R.id.name_input)

        updateButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()

            // Retrieve the token from SharedPreferences
            val token = sharedPreferences.getString("TOKEN", "")

            if (!token.isNullOrEmpty() && email.isNotEmpty() && name.isNotEmpty()) {
                // Pass the token, email, and password to updateUserProfile
                authService.updateUserProfile(token, email, name) { success, message ->
                    if (success) {
                        // Handle success (e.g., show a success message)
                        Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle failure (e.g., show error message)
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Handle missing token or empty email/password
                Toast.makeText(context, "Token is missing or email/password is empty", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}

