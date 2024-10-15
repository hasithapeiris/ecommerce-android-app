package com.example.ecommerceapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CommentAdapter
import com.example.ecommerceapp.models.CommentItem
import com.example.ecommerceapp.models.CommentModel
import com.example.ecommerceapp.services.AuthService

class CommentFragment : Fragment() {

    private lateinit var rvComments: RecyclerView
    private lateinit var commentsAdapter: CommentAdapter
    private lateinit var commentList: MutableList<CommentItem> // Change this to CommentModel
    private lateinit var etComment: EditText
    private lateinit var btnSubmitComment: Button
    private lateinit var ratingBar: RatingBar
    private val authService = AuthService()
    private lateinit var vendorId: String
    private lateinit var customerId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        // Retrieve arguments
        val args: CommentFragmentArgs by navArgs()
        vendorId = args.vendorId

        // Initialize views
        rvComments = view.findViewById(R.id.rvComments)
        etComment = view.findViewById(R.id.etComment)
        btnSubmitComment = view.findViewById(R.id.btnSubmitComment)
        ratingBar = view.findViewById(R.id.ratingBar)

        // Setup RecyclerView for comments
        rvComments.layoutManager = LinearLayoutManager(requireContext())
        commentList = mutableListOf()
        commentsAdapter = CommentAdapter(commentList)
        rvComments.adapter = commentsAdapter

        // Fetch customerId from Shared Preferences
        fetchCustomerId()

        // Fetch existing comments when the fragment is created
        fetchComments()

        // Setup submit comment functionality
        btnSubmitComment.setOnClickListener {
            val commentText = etComment.text.toString()
            val rating = ratingBar.rating.toInt()

            if (commentText.isNotEmpty() && rating > 0) {
                // Submit the review using AuthService with dynamic vendor ID
                authService.submitReview(
                    requireContext(),
                    vendorId,
                    commentText,
                    rating
                ) { success, message ->
                    if (success) {
                        // Fetch updated comments from backend to show the new comment
                        fetchComments() // This will reload the comments, including the new one

                        // Clear input fields
                        etComment.text.clear()
                        ratingBar.rating = 0f
                    } else {
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Please write a comment and rate the vendor", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun fetchCustomerId() {
        // Retrieve customerId from Shared Preferences
        val sharedPreferences = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        customerId = sharedPreferences.getString("CUSTOMER_ID", "") ?: ""
    }

    private fun fetchComments() {
        if (customerId.isNotEmpty()) {
            authService.getCommentsForVendor(requireContext(), vendorId) { success, response, error ->
                if (success && response != null) {
                    commentList.clear() // Clear the current list

                    // Add all comments from the response data
                    commentList.addAll(response.data) // This will work now since the entire response is passed

                    commentsAdapter.notifyDataSetChanged() // Notify the adapter to refresh
                } else {
                    Toast.makeText(requireContext(), "Failed to load comments: $error", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Customer ID is not available.", Toast.LENGTH_SHORT).show()
        }
    }


}
