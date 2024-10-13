/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: Fragment view handling for reset password fragment
 *****/
package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.ecommerceapp.middlewares.Extensions.toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.services.AuthService

class ResetPassword : Fragment(R.layout.fragment_resetpassword) {

    private lateinit var authService: AuthService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authService = AuthService()

        val newPasswordEditText: EditText = view.findViewById(R.id.et_new_password)
        val resetPasswordButton: Button = view.findViewById(R.id.btn_reset_password)

        resetPasswordButton.setOnClickListener {
            val newPassword = newPasswordEditText.text.toString().trim()

            if (newPassword.isNotEmpty()) {
                resetPassword(newPassword)
            } else {
                requireActivity().toast("Please enter a new password")
            }
        }
    }
//handle reset password in fragment view
    private fun resetPassword(newPassword: String) {
        // Retrieve email from SharedPreferences or pass it as needed
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("EMAIL", "")
        if (email.isNullOrEmpty()) {
            requireActivity().toast("Email not found")
            return
        }

        authService.resetPassword(requireContext(), email, newPassword) { success, message ->
            if (success) {
                requireActivity().toast("Password reset successful")
                // Navigate back or to another fragment if needed
            } else {
                requireActivity().toast(message ?: "Password reset failed")
            }
        }
    }
}
