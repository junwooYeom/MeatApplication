package com.junwooyeom.meatapplication.domain.model

sealed class NetworkResult<out T> {
    object Loading: NetworkResult<Nothing>()
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Failure(val throwable: Throwable? = null): NetworkResult<Nothing>()
}
