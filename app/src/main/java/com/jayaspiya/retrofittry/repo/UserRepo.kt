package com.jayaspiya.retrofittry.repo

import com.jayaspiya.retrofittry.api.MyApiRequest
import com.jayaspiya.retrofittry.api.ServiceBuilder
import com.jayaspiya.retrofittry.api.UserAPI
import com.jayaspiya.retrofittry.model.User
import com.jayaspiya.retrofittry.response.UserResponse

class UserRepo: MyApiRequest() {
    private val userApi = ServiceBuilder.buildService(UserAPI::class.java)
    suspend fun registerUser(user: User): UserResponse{
        return apiRequest {
            userApi.registerUser(user)
        }
    }
    suspend fun loginUser(user: User): UserResponse{
        return apiRequest {
            userApi.loginUser(user)
        }
    }
}