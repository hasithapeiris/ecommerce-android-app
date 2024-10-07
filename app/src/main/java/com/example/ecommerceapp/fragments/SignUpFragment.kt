package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.example.ecommerceapp.middlewares.Extensions.toast
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentSignUpBinding
import com.example.ecommerceapp.services.AuthService

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var authService: AuthService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpBinding.bind(view)
        authService = AuthService()

        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmailSignUp.text.toString()
            val name = binding.etNameSignUp.text.toString()
            val password = binding.etPasswordSignUp.text.toString()

            if (email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty()) {
                createUser(email, password)
            } else {
                requireActivity().toast("Please fill in all fields")
            }
        }

        binding.tvNavigateToSignIn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    /**
     * Handles user creation process using AuthService.
     * @param email The user's email.
     * @param password The user's password.
     */
    private fun createUser(email: String, password: String) {
        authService.createUser(email, password) { success, errorMessage ->
            if (success) {
                requireActivity().toast("New user created")
                navigateToMainFragment()
            } else {
                requireActivity().toast(errorMessage ?: "User creation failed")
            }
        }
    }

    /**
     * Navigate to the main fragment after successful user creation.
     */
    private fun navigateToMainFragment() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_signUpFragment_to_mainFragment2)
    }
}
