package com.example.ecommerceapp.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ecommerceapp.R
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.api.UpdateProfileApi
import com.example.ecommerceapp.models.DeactivateResponse
import com.example.ecommerceapp.models.UserDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Userprofile : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_userprofile, container, false)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        token = sharedPreferences.getString("TOKEN", null) // Retrieve the token here

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        token?.let {
            // Fetch and display the user's profile
            getUserProfile(it) { success, userDetail ->
                if (success && userDetail != null) {
                    displayUserProfile(view, userDetail)
                } else {
                    Toast.makeText(requireContext(), "Failed to load user profile", Toast.LENGTH_SHORT).show()
                }
            }
        } ?: run {
            Toast.makeText(requireContext(), "User not authenticated.", Toast.LENGTH_SHORT).show()
        }

        val changePasswordButton: Button = view.findViewById(R.id.changepassbutton)
        changePasswordButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_userprofile_to_changepassword)
        }

        val updateProfileButton: Button = view.findViewById(R.id.button2)
        updateProfileButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_userprofile_to_profileUpdate)
        }

        val notificationIcon: ImageView = view.findViewById(R.id.imageView)
        notificationIcon.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_userprofile_to_notificationsFragment)
        }

        val deactivateButton: Button = view.findViewById(R.id.btn_reset_password4)
        deactivateButton.setOnClickListener {
            token?.let {
                deactivateUserProfile(it)
            } ?: run {
                Toast.makeText(requireContext(), "User not authenticated.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Display the user profile in the UI
    private fun displayUserProfile(view: View, userDetail: UserDetail) {
        val userIdTextView: TextView = view.findViewById(R.id.user_id)
        val userNameTextView: TextView = view.findViewById(R.id.user_name)
        val userEmailTextView: TextView = view.findViewById(R.id.user_email)
        val userRoleTextView: TextView = view.findViewById(R.id.user_role)

        userIdTextView.text = userDetail.id
        userNameTextView.text = userDetail.name
        userEmailTextView.text = userDetail.email
        userRoleTextView.text = userDetail.role
    }

    // Method to deactivate user profile with API call
    private fun deactivateUserProfile(token: String) {
        val UpdateProfileApi =
            RetrofitInstance.instance.create(UpdateProfileApi::class.java)
        val call = UpdateProfileApi.deactivateUser("Bearer $token")

        call.enqueue(object : Callback<DeactivateResponse> {
            override fun onResponse(call: Call<DeactivateResponse>, response: Response<DeactivateResponse>) {
                if (response.isSuccessful && response.body()?.success == true) {
                    sharedPreferences.edit().clear().apply()
                    Toast.makeText(requireContext(), "Account deactivated. Please login again.", Toast.LENGTH_SHORT).show()
                     Navigation.findNavController(requireView()).navigate(R.id.action_userprofile_to_signInFragment)
                } else {
                    Toast.makeText(requireContext(), response.body()?.message ?: "Deactivation failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DeactivateResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Fetch user profile data (example function)
    private fun getUserProfile(token: String, callback: (Boolean, UserDetail?) -> Unit) {
        // Implement your method to fetch the user profile using the token
    }
}
