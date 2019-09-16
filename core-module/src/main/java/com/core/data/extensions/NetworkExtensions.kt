package com.core.data.extensions

import com.core.data.model.BaseCoinmarketResponse
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response

fun <RESPONSE_TYPE, BASE_RESPONSE : BaseCoinmarketResponse<RESPONSE_TYPE>, RESULT_TYPE> Call<BASE_RESPONSE>.getCoinmarketResult(
    successConverter: (RESPONSE_TYPE) -> RESULT_TYPE,
    errorConverter: (Int, String, Response<BASE_RESPONSE?>?, Throwable?) -> RESULT_TYPE
) = try {
    val response = execute()
    if (response.isSuccessful) {
        successConverter(response.body()!!.data!!)
    } else {
        errorConverter(response.code(), response.message(), response, null)
    }
} catch (throwable: Throwable) {
    errorConverter(-1, throwable.toErrorString(), null, throwable)
}

private fun Throwable.toErrorString() =
    when (this) {
        is HttpException -> "${code()}: $message"
        else -> localizedMessage ?: ""
    }