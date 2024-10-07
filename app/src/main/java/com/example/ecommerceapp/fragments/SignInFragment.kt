package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.example.ecommerceapp.middlewares.Extensions.toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSignInBinding
import com.example.ecommerceapp.services.AuthService

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var authService: AuthService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignInBinding.bind(view)
        authService = AuthService()

        // Check if user is already logged in
        if (authService.isUserLoggedIn()) {
            navigateToMainFragment()
        }

        // Sign-in button click listener
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmailSignIn.text.toString()
            val password = binding.etPasswordSignIn.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInUser(email, password)
            } else {
                requireActivity().toast("Some fields are empty")
            }
        }

        // Navigate to sign-up fragment
        binding.tvNavigateToSignUp.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                .navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    /**
     * Handles user sign-in process using AuthService.
     * @param email The user's email.
     * @param password The user's password.
     */
    private fun signInUser(email: String, password: String) {
        authService.signIn(email, password) { success, errorMessage ->
            if (success) {
                requireActivity().toast("Sign-in successful")
                navigateToMainFragment()
            } else {
                requireActivity().toast(errorMessage ?: "Sign-in failed")
            }
        }
    }

    /**
     * Navigate to the main fragment after successful sign-in.
     */
    private fun navigateToMainFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            .navigate(R.id.action_signInFragment_to_mainFragment2)
    }
}