/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: Fragment view handling for Forgot password and 2FA authentication fragment
 *****/

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
    private lateinit var emailEditText3: EditText
    private lateinit var codeEditText: EditText
    private lateinit var btnResetPassword2: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        // Initialize views for sending reset link
        emailEditText = view.findViewById(R.id.emailEditText)
        submitButton = view.findViewById(R.id.submitButton)

        // Initialize views for 2FA verification
        emailEditText3 = view.findViewById(R.id.emailEditText3)
        codeEditText = view.findViewById(R.id.code)
        btnResetPassword2 = view.findViewById(R.id.btn_reset_password2)

        // Set click listener for sending reset link
        submitButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            if (email.isNotEmpty()) {
                sendForgotPasswordRequest(email)
            } else {
                Toast.makeText(requireContext(), "Please enter your email", Toast.LENGTH_SHORT).show()
            }
        }

        // Set click listener for 2FA verification
        btnResetPassword2.setOnClickListener {
            val email = emailEditText3.text.toString().trim()
            val code = codeEditText.text.toString().trim()

            if (email.isNotEmpty() && code.isNotEmpty()) {
                send2faVerificationRequest(email, code)
            } else {
                Toast.makeText(requireContext(), "Please enter both email and code", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    // Handle forgot password in fragment view
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

    // Handle 2FA verification in fragment view
    private fun send2faVerificationRequest(email: String, code: String) {
        val authService = AuthService()
        authService.verifyTwoFactor(requireContext(), email, code) { success, message ->
            if (success) {
                Toast.makeText(requireContext(), message ?: "Two-factor authentication successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), message ?: "Error verifying 2FA", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
