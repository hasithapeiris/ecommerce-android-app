package com.example.ecommerceapp.api

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
}



