package com.jayaspiya.retrofittry.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "http://192.168.1.110:3000/api/v1/"
    var token: String? = null
    // Create retrofit instance
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
    // Generic function
    fun <T> buildService(serviceType: Class<T>): T{
        return retrofitBuilder.create(serviceType)
    }
}