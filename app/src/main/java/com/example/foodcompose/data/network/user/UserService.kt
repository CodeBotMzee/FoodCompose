package com.example.foodcompose.data.network.user

import com.example.foodcompose.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

 interface UserService {

    @POST("/signup")
    suspend fun userSignUp(@Body user: User): Response<User>

}