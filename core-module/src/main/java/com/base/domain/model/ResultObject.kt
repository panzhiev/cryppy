package com.base.domain.model

private const val ERROR_UNAUTHORIZED = "Unauthorized"
private const val ERROR_CONNECTION = "Connection error"
private const val ERROR_CONNECTION_TIMEOUT = "Connection timeout"
private const val ERROR_SERVER_INTERNAL = "Internal server error"

sealed class ResultObject<out RESPONSE_TYPE> {
    class Success<TYPE>(val data: TYPE) : ResultObject<TYPE>()
    class Error(val cause: BaseErrorCause) : ResultObject<Nothing>()
}

interface BaseErrorCause {
    val errorMessage: String
}

sealed class NetworkErrorCause(override val errorMessage: String) : BaseErrorCause {
    object Unauthorized : NetworkErrorCause(ERROR_UNAUTHORIZED)
    object Connection : NetworkErrorCause(ERROR_CONNECTION)
    object ConnectionTimeout : NetworkErrorCause(ERROR_CONNECTION_TIMEOUT)
    object ServerInternal : NetworkErrorCause(ERROR_SERVER_INTERNAL)
    data class Unknown(override val errorMessage: String = "") : NetworkErrorCause(errorMessage)
}