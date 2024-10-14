/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Cart service for handling cart related APIs.
 * *****/

package com.example.ecommerceapp.services
import com.example.ecommerceapp.api.CartApi
import com.example.ecommerceapp.api.ProductApi
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.CartModel
import com.example.ecommerceapp.models.CartResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartService {
    private val cartApi: CartApi = RetrofitInstance.instance.create(CartApi::class.java)

    // Add item to the cart
    fun addItemToCart(cartItem: CartModel, token: String, callback: (Boolean, String?) -> Unit) {
        val call = cartApi.addCartItem(cartItem, "Bearer $token")
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback(false, "Error: ${t.message}")
            }
        })
    }

    // Fetch cart items
    fun getCartItems(token: String, callback: (List<CartResponse>?, String?) -> Unit) {
        val call = cartApi.getCartItems("Bearer $token")
        call.enqueue(object : Callback<List<CartResponse>> {
            override fun onResponse(call: Call<List<CartResponse>>, response: Response<List<CartResponse>>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    val errorMessage = when (response.code()) {
                        401 -> "Unauthorized: Please log in."
                        500 -> "Server error: Please try again later."
                        else -> "Failed to fetch cart items."
                    }
                    callback(null, errorMessage)
                }
            }

            override fun onFailure(call: Call<List<CartResponse>>, t: Throwable) {
                callback(null, "Error: ${t.message}")
            }
        })
    }
}