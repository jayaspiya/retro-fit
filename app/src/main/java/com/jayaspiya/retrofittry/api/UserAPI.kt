package com.jayaspiya.retrofittry.api

import com.jayaspiya.retrofittry.model.User
import com.jayaspiya.retrofittry.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("auth/register")
    suspend fun registerUser(
        @Body user: User
    ): Response<UserResponse>

    @POST("auth/login")
    suspend fun loginUser(
        @Body user: User
    ): Response<UserResponse>
}