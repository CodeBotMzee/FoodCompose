package com.example.foodcompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}