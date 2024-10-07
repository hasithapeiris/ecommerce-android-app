package com.example.ecommerceapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://192.168.1.4:7128" // Replace with correct server IP
    //"https://192.168.1.4:7128" comments
        //"https://10.0.2.2:7128" comments
    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}



