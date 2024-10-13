package com.example.ecommerceapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CommentAdapter
import com.example.ecommerceapp.models.CommentModel

class CommentFragment : Fragment() {

    private lateinit var rvComments: RecyclerView
    private lateinit var commentsAdapter: CommentAdapter
    private lateinit var commentList: MutableList<CommentModel>
    private lateinit var etComment: EditText
    private lateinit var btnSubmitComment: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        // Initialize the views
        rvComments = view.findViewById(R.id.rvComments)
        etComment = view.findViewById(R.id.etComment)
        btnSubmitComment = view.findViewById(R.id.btnSubmitComment)

        // Set up RecyclerView
        rvComments.layoutManager = LinearLayoutManager(requireContext())
        commentList = mutableListOf(
            // Sample data (replace with actual data from your backend or database)
            CommentModel("Alice", "Great product!", "https://example.com/profile_alice.jpg"),
            CommentModel("Bob", "Highly recommend!", "https://example.com/profile_bob.jpg")
        )

        commentsAdapter = CommentAdapter(commentList)
        rvComments.adapter = commentsAdapter

        // Submit comment action
        btnSubmitComment.setOnClickListener {
            val newCommentText = etComment.text.toString()
            if (newCommentText.isNotEmpty()) {
                // Assuming you get the username and profileImage from user session
                val newComment = CommentModel("CurrentUser", newCommentText, "https://example.com/profile_current_user.jpg")

                // Add comment to list and update RecyclerView
                commentList.add(newComment)
                commentsAdapter.notifyItemInserted(commentList.size - 1)

                // Clear the input field
                etComment.text.clear()

                // Optionally scroll to the last comment
                rvComments.scrollToPosition(commentList.size - 1)
            } else {
                Toast.makeText(requireContext(), "Please write a comment", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

}