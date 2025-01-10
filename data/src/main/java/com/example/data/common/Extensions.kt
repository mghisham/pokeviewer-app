package com.example.data.common

import retrofit2.HttpException
import retrofit2.Response

inline fun <reified T> safeApiCall(apiCall: () -> Response<T>): Result<T> = runCatching {
    apiCall.invoke().run {
        if (isSuccessful) {
            body() ?: throw IllegalStateException("Response body is null")
        } else {
            throw HttpException(this)
        }
    }
}
