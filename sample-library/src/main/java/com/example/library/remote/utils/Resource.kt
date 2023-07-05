package com.example.library.remote.utils


sealed class Resource<out T > {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Exceptions) : Resource<Nothing>()
}

suspend fun <T : Any> safeApiCall(
    call: suspend () -> Resource<T>,
    errorMessage: String
): Resource<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Resource.Error(Exceptions.IOException(message = errorMessage, cause = e))
    }
}