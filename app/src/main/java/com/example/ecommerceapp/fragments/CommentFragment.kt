//CommentFragment.kt
// Import necessary libraries
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CommentAdapter
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.api.ReviewApi
import com.example.ecommerceapp.models.CommentModel
import com.example.ecommerceapp.models.ReviewRequest
import com.example.ecommerceapp.models.ReviewResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentFragment : Fragment() {

    private lateinit var rvComments: RecyclerView
    private lateinit var commentsAdapter: CommentAdapter
    private lateinit var commentList: MutableList<CommentModel>
    private lateinit var etComment: EditText
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSubmitComment: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        rvComments = view.findViewById(R.id.rvComments)
        etComment = view.findViewById(R.id.etComment)

        btnSubmitComment = view.findViewById(R.id.btnSubmitComment)

        rvComments.layoutManager = LinearLayoutManager(requireContext())
        commentList = mutableListOf()
        commentsAdapter = CommentAdapter(commentList)
        rvComments.adapter = commentsAdapter

        btnSubmitComment.setOnClickListener {
            val newCommentText = etComment.text.toString()
            val rating = ratingBar.rating.toInt()

            if (newCommentText.isNotEmpty()) {
                submitComment("vendorId", "customerId", newCommentText, rating)
            } else {
                Toast.makeText(requireContext(), "Please write a comment", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun submitComment(vendorId: String, customerId: String, comment: String, rating: Int) {
        val reviewApi = RetrofitInstance.instance.create(ReviewApi::class.java)
        val reviewRequest = ReviewRequest(vendorId, customerId, comment, rating)

        reviewApi.submitReview(reviewRequest).enqueue(object : Callback<ReviewResponse> {
            override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                if (response.isSuccessful) {
                    val newReview = response.body()?.data
                    if (newReview != null) {
                        commentList.add(CommentModel(newReview.customerId, newReview.comment))
                        commentsAdapter.notifyItemInserted(commentList.size - 1)
                        etComment.text.clear()
                        ratingBar.rating = 0f
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to submit comment", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}