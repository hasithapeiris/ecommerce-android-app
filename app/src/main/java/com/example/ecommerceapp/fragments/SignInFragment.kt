/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: Fragment view handling for sign in fragment
 *****/

package com.example.ecommerceapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ecommerceapp.Extensions.toast
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

        // Sign-in button click listener
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmailSignIn.text.toString().trim()
            val password = binding.etPasswordSignIn.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInUser(email, password)
            } else {
                requireActivity().toast("Some fields are empty")
            }
        }
        binding.tvNavigateToSignUp2.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_forgotPassword)
        }
    }


    //handle sign in fragment view
    private fun signInUser(email: String, password: String) {
        authService.signIn(requireContext(), email, password) { success, errorMessage ->
            if (success) {
                requireActivity().toast("Sign-in successful")
                navigateToMainFragment()
            } else {
                requireActivity().toast(errorMessage ?: "Sign-in failed")
            }
        }
    }

    private fun navigateToMainFragment() {
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
            .navigate(R.id.action_signInFragment_to_mainFragment2)
    }
}
