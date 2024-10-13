/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * description: Cart service for handling cart related APIs.
 * *****/

package com.example.ecommerceapp.services
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.models.CartModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartService {
//    private val api = RetrofitInstance.cartApi

    // Fetch cart items by userId
    fun getCartItemsByUserId(userId: String, callback: (List<CartModel>?, String?) -> Unit) {
//        api.getCartItemsByUserId(userId).enqueue(object : Callback<List<CartModel>> {
//            override fun onResponse(call: Call<List<CartModel>>, response: Response<List<CartModel>>) {
//                if (response.isSuccessful) {
//                    callback(response.body(), null)
//                } else {
//                    callback(null, "Error ${response.code()}: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<List<CartModel>>, t: Throwable) {
//                callback(null, t.localizedMessage ?: "Network error. Please try again.")
//            }
//        })
    }

    // Remove an item from the cart
    fun removeCartItem(item: CartModel, callback: (Boolean, String?) -> Unit) {
//        val itemId = item.pid ?: return callback(false, "Item ID is null")
//
//        api.removeCartItem(itemId).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.isSuccessful) {
//                    callback(true, "Item removed from cart successfully.")
//                } else {
//                    callback(false, "Error ${response.code()}: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                callback(false, t.localizedMessage ?: "Network error. Please try again.")
//            }
//        })
    }
}