/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: Fragment view handling for Sign up fragment
 *****/
package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.example.ecommerceapp.Extensions.toast
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
            val passwordConfirmation = binding.etPasswordSignUp2.text.toString() // Add this field

            if (email.isNotEmpty() && name.isNotEmpty() && password.isNotEmpty() && passwordConfirmation.isNotEmpty()) {
                if (password == passwordConfirmation) {
                    createUser(email, name, password, passwordConfirmation)
                } else {
                    requireActivity().toast("Passwords do not match")
                }
            } else {
                requireActivity().toast("Please fill in all fields")
            }
        }

        binding.tvNavigateToSignIn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    //handle user creation in fragment view
    private fun createUser(email: String, name: String, password: String, passwordConfirmation: String) {
        authService.createUser(email, name, password, passwordConfirmation) { success, errorMessage ->
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
