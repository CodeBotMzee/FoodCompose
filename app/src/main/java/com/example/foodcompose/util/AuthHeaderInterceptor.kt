package com.example.foodcompose.util

import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor: Interceptor {
    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        Log.d("Current User","${firebaseAuth.currentUser}")

        val user = firebaseAuth.currentUser ?: return chain.proceed(original)

        val tokenResult = Tasks.await(user.getIdToken(false))

        val requestBuilder = original.newBuilder()
            .header("Authorization", "Bearer ${tokenResult!!.token!!}")

        Log.d("AuthHeaderInterceptor","Bearer ${tokenResult.token!!}")


        return chain.proceed(requestBuilder.build())
    }
}