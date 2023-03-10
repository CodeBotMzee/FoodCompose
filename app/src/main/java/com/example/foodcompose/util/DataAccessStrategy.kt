package com.example.foodcompose.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.foodcompose.util.Resource.Status.SUCCESS
import com.example.foodcompose.util.Resource.Status.ERROR
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())


        val source = databaseQuery.invoke().map {
            Resource.success(it)
        } as LiveData<Resource<T>>
        emitSource(source)


        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)


        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <E> performPostOperation(
    networkCall: suspend () -> Resource<E>,
    saveCallResult: suspend (E) -> Unit
): LiveData<Resource<E>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val response = networkCall.invoke()

        if (response.status == SUCCESS) {
            saveCallResult(response.data!!)
            emit(Resource.success(response.data))
        } else if (response.status == ERROR) {
            emit(Resource.error(response.message!!))
        }
    }