package com.example.myreditt.core.networking

/**
 * Sealed class for handling network states
 */
sealed class DataResult<T> {
    data class Progress<T>(var loading: Boolean) : DataResult<T>()
    data class Success<T>(var data: T) : DataResult<T>()
    data class Failure<T>(val e: Throwable) : DataResult<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): DataResult<T> = Progress(isLoading)

        fun <T> success(data: T): DataResult<T> = Success(data)

        fun <T> failure(e: Throwable): DataResult<T> = Failure(e)
    }
}