/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Fragment to handling main page.
 *****/

package com.example.ecommerceapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CategoryOnClickInterface
import com.example.ecommerceapp.adapters.ProductAdapter
import com.example.ecommerceapp.adapters.ItemOnClickInterface
import com.example.ecommerceapp.adapters.MainCategoryAdapter
import com.example.ecommerceapp.databinding.FragmentMainBinding
import com.example.ecommerceapp.models.CategoryModel
import com.example.ecommerceapp.models.ProductModel
import com.example.ecommerceapp.services.AuthService
import com.example.ecommerceapp.services.ProductService
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main), CategoryOnClickInterface,
    ItemOnClickInterface{
    private lateinit var binding: FragmentMainBinding
    private lateinit var productService: ProductService
    private lateinit var authService: AuthService
    private lateinit var categoryAdapter: MainCategoryAdapter
    private lateinit var productsAdapter: ProductAdapter
    private lateinit var productList: ArrayList<ProductModel>
    private lateinit var categoryList: ArrayList<CategoryModel>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        productList = ArrayList()
        categoryList = ArrayList()

        // Initialize services
        productService = ProductService()
        authService = AuthService()

        // Set up category RecyclerView
        binding.rvMainCategories.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        categoryAdapter = MainCategoryAdapter(categoryList, this)
        binding.rvMainCategories.adapter = categoryAdapter

        // Set up product RecyclerView
        binding.rvMainProductsList.layoutManager = GridLayoutManager(context, 1)
        productsAdapter = ProductAdapter(requireContext(), productList, this)
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
                R.id.ordersFragment -> {
                    Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment2_to_ordersFragment2)
                    true
                }
                R.id.profileFragment -> {
                    authService.signOut()
                    Navigation.findNavController(requireView()).navigate(R.id.action_mainFragment2_to_userprofile2)
                    true
                }
                else -> false
            }
        }
    }

    private fun setCategoryList() {
        productService.getCategories { categories ->
            categoryList.clear()
            categoryList.add(CategoryModel("0", "All", true, ""))
            categoryList.addAll(categories)
            categoryAdapter.notifyDataSetChanged()
        }
    }

    private fun setProductsData() {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            val result = productService.getProducts()
            result.onSuccess { products ->
                binding.progressBar.visibility = View.GONE

                productList.clear()
                productList.addAll(products)
                productsAdapter.notifyDataSetChanged()
            }.onFailure { exception ->
                Toast.makeText(requireContext(), "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClickCategory(category: CategoryModel) {
        binding.tvMainCategories.text = category.name
        if (category.name == "All") {
            setProductsData()
        } else {
            productService.getProductsByCategory(category.id) { products ->
                productList.clear()
                productList.addAll(products)
                productsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClickItem(item: ProductModel) {
        val direction = MainFragmentDirections.actionMainFragment2ToDetailsFragment(item.id)
        Navigation.findNavController(requireView())
            .navigate(direction)
    }

}