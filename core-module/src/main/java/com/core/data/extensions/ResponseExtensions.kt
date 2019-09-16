package com.core.data.extensions

import retrofit2.Response

private const val CODE_UNAUTHORIZED = 401
private const val CODE_INTERNAL_SERVER_ERROR = 500

val Response<*>.isCodeUnauthorized
    get() = code() == CODE_UNAUTHORIZED

val Response<*>.isCodeServerError
    get() = code() == CODE_INTERNAL_SERVER_ERROR