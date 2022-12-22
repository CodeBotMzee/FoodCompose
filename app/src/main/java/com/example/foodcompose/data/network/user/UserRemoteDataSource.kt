package com.example.foodcompose.data.network.user

import com.example.foodcompose.data.model.User
import com.example.foodcompose.data.network.BaseDataSource
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userService: UserService):BaseDataSource() {

    suspend fun userSignUp(user: User) = getResult { userService.userSignUp(user) }
}