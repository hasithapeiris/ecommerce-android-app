package com.example.ecommerceapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ecommerceapp.R
import com.example.ecommerceapp.services.AuthService

class ForgotPassword : Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var submitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        emailEditText = view.findViewById(R.id.emailEditText)
        submitButton = view.findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                sendForgotPasswordRequest(email)
            } else {
                Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun sendForgotPasswordRequest(email: String) {
        val authService = AuthService()
        authService.forgotPassword(requireContext(), email) { success, message ->
            if (success) {
                Toast.makeText(requireContext(), message ?: "Password reset email sent", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), message ?: "Error sending reset email", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
