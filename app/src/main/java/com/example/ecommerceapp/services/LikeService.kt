package com.example.ecommerceapp.services

import com.example.ecommerceapp.models.LikeModel

class LikeService {
    /**
     * Fetch the liked products for the current user.
     * @param callback A callback function to return the list of liked products and an optional error message.
     */
    fun fetchLikedProducts(callback: (List<LikeModel>, String?) -> Unit) {

    }

    /**
     * Remove a product from the liked products list.
     * @param likeModel The product to be removed.
     * @param callback A callback function to return success status and an optional error message.
     */
    fun removeLikedProduct(likeModel: LikeModel, callback: (Boolean, String?) -> Unit) {

    }
}