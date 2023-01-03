package com.example.foodcompose.module

import android.content.Context
import com.example.foodcompose.data.authentication.FirebaseSource
import com.example.foodcompose.data.network.user.UserRemoteDataSource
import com.example.foodcompose.data.network.user.UserService
import com.example.foodcompose.data.repository.AuthRepository
import com.example.foodcompose.data.repository.UserRepository
import com.example.foodcompose.data.room.AppDatabase
import com.example.foodcompose.data.room.dao.UserDao
import com.example.foodcompose.util.AuthHeaderInterceptor
import com.example.foodcompose.util.Converters
import com.example.foodcompose.util.LoggingInterceptor
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =Retrofit.Builder()
        .baseUrl("http://0.0.0.0:3001")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(AuthHeaderInterceptor())
            .addInterceptor(LoggingInterceptor())
            .build()
    }

    @Singleton
    @Provides
    fun provideRoomConverter(gson: Gson) = Converters(gson)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context, converters: Converters) =
        AppDatabase.getDatabase(appContext, converters)
    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseSource(firebaseAuth: FirebaseAuth) = FirebaseSource(firebaseAuth)

    @Singleton
    @Provides
    fun provideAuthRepository(firebaseSource: FirebaseSource) = AuthRepository(firebaseSource)
    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit):UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(userService: UserService) = UserRemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideUserRepository(remoteDataSource: UserRemoteDataSource,roomDataSource: UserDao) = UserRepository(remoteDataSource, roomDataSource)
}