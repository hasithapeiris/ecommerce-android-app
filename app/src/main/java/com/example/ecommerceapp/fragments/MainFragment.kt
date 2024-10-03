package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CategoryOnClickInterface
import com.example.ecommerceapp.adapters.ItemAdapter
import com.example.ecommerceapp.adapters.ItemOnClickInterface
import com.example.ecommerceapp.adapters.LikeOnClickInterface
import com.example.ecommerceapp.adapters.MainCategoryAdapter
import com.example.ecommerceapp.databinding.FragmentMainBinding
import com.example.ecommerceapp.models.ItemModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.ItemService

class MainFragment : Fragment(R.layout.fragment_main), CategoryOnClickInterface,
    ItemOnClickInterface, LikeOnClickInterface {
    private lateinit var binding: FragmentMainBinding
    private lateinit var itemService: ItemService
    private lateinit var authService: AuthService
    private lateinit var categoryAdapter: MainCategoryAdapter
    private lateinit var productsAdapter: ItemAdapter
    private lateinit var productList: ArrayList<ItemModel>
    private lateinit var categoryList: ArrayList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        productList = ArrayList()
        categoryList = ArrayList()

        // Initialize services
        itemService = ItemService()
        authService = AuthService()

        // Set up category RecyclerView
        categoryList.add("Trending")
        binding.rvMainCategories.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        categoryAdapter = MainCategoryAdapter(categoryList, this)
        binding.rvMainCategories.adapter = categoryAdapter

        // Set up product RecyclerView
        binding.rvMainProductsList.layoutManager = GridLayoutManager(context, 2)
        productsAdapter = ItemAdapter(requireContext(), productList, this, this)
        binding.rvMainProductsList.adapter = productsAdapter

        // Fetch data
        setCategoryList()
        setProductsData()

        // Handle bottom navigation
        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.mainFragment -> true
                R.id.likeFragment -> {
                    Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment2_to_likeFragment2)
                    true
                }
                R.id.cartFragment -> {
                    Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment2_to_cartFragment2)
                    true
                }
                R.id.profileFragment -> {
                    authService.signOut()
                    Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment2_to_signInFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setCategoryList() {
        itemService.getCategories { categories ->
            categoryList.clear()
            categoryList.add("Trending")
            categoryList.addAll(categories)
            categoryAdapter.notifyDataSetChanged()
        }
    }

    private fun setProductsData() {
        itemService.getItems { products ->
            productList.clear()
            productList.addAll(products)
            productsAdapter.notifyDataSetChanged()
        }
    }

    override fun onClickCategory(button: Button) {
        binding.tvMainCategories.text = button.text.toString()
        if (button.text == "Trending") {
            setProductsData()
        } else {
            itemService.getItemsByCategory(button.text.toString()) { products ->
                productList.clear()
                productList.addAll(products)
                productsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClickItem(item: ItemModel) {
        val direction = MainFragmentDirections
            .actionMainFragment2ToDetailsFragment()

        Navigation.findNavController(requireView())
            .navigate(direction)
    }

    override fun onClickLike(item: ItemModel) {
        // Handle adding liked products here (e.g., call LikeService)
    }
}