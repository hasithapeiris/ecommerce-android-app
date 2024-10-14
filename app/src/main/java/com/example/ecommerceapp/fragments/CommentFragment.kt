package com.example.ecommerceapp.fragments

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
import com.example.ecommerceapp.models.CommentModel
import com.example.ecommerceapp.services.AuthService

class CommentFragment : Fragment() {

    private lateinit var rvComments: RecyclerView
    private lateinit var commentsAdapter: CommentAdapter
    private lateinit var commentList: MutableList<CommentModel>
    private lateinit var etComment: EditText
    private lateinit var btnSubmitComment: Button
    private lateinit var ratingBar: RatingBar
    private val authService = AuthService()
    private lateinit var vendorId: String // Class-level vendor ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        // Retrieve arguments
        val args: CommentFragmentArgs by navArgs()
        vendorId = args.vendorId // Assign to the class-level vendorId

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

        // Setup submit comment functionality
        btnSubmitComment.setOnClickListener {
            val commentText = etComment.text.toString()
            val rating = ratingBar.rating.toInt()

            if (commentText.isNotEmpty() && rating > 0) {
                // Submit the review using AuthService with dynamic vendor ID
                authService.submitReview(
                    requireContext(),
                    vendorId, // Use the dynamic vendor ID here
                    commentText,
                    rating
                ) { success, message ->
                    if (success) {
                        // Add new comment to the list and notify adapter
                        val newComment = CommentModel("Anonymous", commentText) // Change "Anonymous" if needed
                        commentList.add(newComment)
                        commentsAdapter.notifyItemInserted(commentList.size - 1)

                        // Clear input fields and scroll to the new comment
                        etComment.text.clear()
                        ratingBar.rating = 0f
                        rvComments.scrollToPosition(commentList.size - 1)
                    }
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please write a comment and rate the vendor", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
