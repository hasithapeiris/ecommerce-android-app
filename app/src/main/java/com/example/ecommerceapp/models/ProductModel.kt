/*****
 * Author: Peiris E.A.H.A
 * STD: IT21175152
 * Description: Product Model.
 *****/

package com.example.ecommerceapp.models

data class ProductModel(
    val id: String,
    val userId: String,
    val vendorInfo: VendorInfo,
    val categoryId: String,
    val name: String,
    val category: CategoryModel,
    val photos: List<String>,
    val condition: String,
    val status: String,
    val description: String,
    val stock: Int,
    val sku: String,
    val price: Float,
    val discount: Float,
    val productWeight: Float,
    val width: Float,
    val height: Float,
    val length: Float,
    val shippingFee: Float
)

data class ProductResponse(
    val success: Boolean,
    val data: List<ProductModel>,
    val message: String? = null,
    val error: String? = null,
    val errorCode: Int? = null,
    val errorData: String? = null
)

data class VendorInfo(
    val id: String,
    val name: String,
    val email: String,
    val role: Int
)

data class CategoryModel(
    val id: String,
    val name: String,
    val isActive: Boolean,
    val image: String
)

data class CategoryResponse(
    val success: Boolean,
    val data: List<CategoryModel>,
    val message: String? = null,
    val error: String? = null,
    val errorCode: Int? = null,
    val errorData: String? = null
)