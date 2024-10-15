// CommentAdapter.kt
package com.example.ecommerceapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.models.CommentItem
import com.example.ecommerceapp.services.AuthService

class CommentAdapter(
    private val commentList: List<CommentItem>,
    private val onDeleteClick: (String) -> Unit // Add a callback for delete action
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
        val tvComment: TextView = itemView.findViewById(R.id.tvComment)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar2)
        val deleteButton: ImageButton = itemView.findViewById(R.id.imageButton) // Update to match your button ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        holder.tvUsername.text = comment.customerId
        holder.tvComment.text = comment.comment
        holder.ratingBar.rating = comment.rating.toFloat()

        // Set delete button click listener
        holder.deleteButton.setOnClickListener {
            onDeleteClick(comment.id) // Pass the comment ID to the callback
        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }
}
