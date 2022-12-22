package com.example.foodcompose.data.repository

import com.example.foodcompose.data.model.User
import com.example.foodcompose.data.network.user.UserRemoteDataSource
import com.example.foodcompose.data.room.dao.UserDao
import com.example.foodcompose.util.performPostOperation
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val roomDataSource: UserDao
) {
    fun signUp(user: User) =
        performPostOperation(
            networkCall = { remoteDataSource.userSignUp(user) },
            saveCallResult = { roomDataSource.addUser(it) }
        )
}