/*****
 * Author: Baddewithana P
 * STD: IT21247804
 * description: retrofitclient instance to make the connection
 * Tutorial: https://medium.com/@ryan.samarajeewa/connect-your-android-app-to-a-local-asp-net-core-app-without-the-headaches-725dfb16e061
 *****/

package com.example.ecommerceapp.api
package com.example.ecommerceapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://192.168.1.4:45455"
    //"https://192.168.1.4:45455" sample for external device
        //"https://10.0.2.2:45455" sample for virtual device
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    val productApi: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }

    val orderApi: OrderApi by lazy {
        retrofit.create(OrderApi::class.java)
    }

    val cartApi: CartApi by lazy {
        retrofit.create(CartApi::class.java)
    }
}