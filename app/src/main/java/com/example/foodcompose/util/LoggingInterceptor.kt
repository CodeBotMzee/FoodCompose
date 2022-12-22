package com.example.foodcompose.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        Log.i("Sending request Url:", " ${request.url}")

        return chain.proceed(request)
    }
}