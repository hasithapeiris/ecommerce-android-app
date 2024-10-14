package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.LikeAdapter
import com.example.ecommerceapp.adapters.LikedOnClickInterface
import com.example.ecommerceapp.adapters.LikedProductOnClickInterface
import com.example.ecommerceapp.databinding.FragmentLikeBinding
import com.example.ecommerceapp.models.LikeModel
import com.example.ecommerceapp.services.LikeService
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.middlewares.Extensions.toast

class LikeFragment : Fragment(R.layout.fragment_like), LikedProductOnClickInterface,
    LikedOnClickInterface {

    private lateinit var binding: FragmentLikeBinding
    private lateinit var adapter: LikeAdapter
    private lateinit var likedProductList: ArrayList<LikeModel>
    private lateinit var likeService: LikeService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLikeBinding.bind(view)
        likedProductList = ArrayList()
        likeService = LikeService()

        binding.likeActualToolbar.setNavigationOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }

        adapter = LikeAdapter(requireContext(), likedProductList, this, this)
        val productLayoutManager = GridLayoutManager(context, 2)
        binding.rvLikedProducts.layoutManager = productLayoutManager
        binding.rvLikedProducts.adapter = adapter

        displayLikedProducts()
    }

    private fun displayLikedProducts() {
        likeService.fetchLikedProducts { likedProducts, errorMessage ->
            if (errorMessage == null) {
                likedProductList.clear()
                likedProductList.addAll(likedProducts)
                adapter.notifyDataSetChanged()
            } else {
                requireActivity().toast(errorMessage)
            }
        }
    }

    override fun onClickProduct(item: LikeModel) {
        // Handle product click
    }

    override fun onClickLike(item: LikeModel) {
        likeService.removeLikedProduct(item) { success, errorMessage ->
            if (success) {
                likedProductList.remove(item)
                adapter.notifyDataSetChanged()
                requireActivity().toast("Removed From the Liked Items")
            } else {
                requireActivity().toast(errorMessage ?: "Failed to remove from liked items")
            }
        }
    }
}