package com.infomodule.data.datasource.network

import com.core.data.extensions.*
import com.core.domain.model.NetworkErrorCause
import com.core.domain.model.ResultObject
import com.infomodule.data.api.CoinmarketService
import com.infomodule.data.model.Crypto
import retrofit2.Response
import javax.inject.Inject

class InfoNetworkDataSourceImpl
@Inject constructor(private val coinmarketService: CoinmarketService) : InfoNetworkDataSource {

    override fun getCryptos(): ResultObject<List<Crypto>> =
        coinmarketService.getCryptos().getCoinmarketResult(
            { data -> ResultObject.Success(data) },
            { _, errorMessage, response, throwable ->
                handleCoinmarkerError(
                    errorMessage,
                    response,
                    throwable
                )
            }
        )

    fun handleCoinmarkerError(
        errorMessage: String,
        response: Response<*>?,
        throwable: Throwable?
    ): ResultObject.Error {
        val cause: NetworkErrorCause = when {
            response?.isCodeUnauthorized == true -> NetworkErrorCause.Unauthorized
            response?.isCodeServerError == true -> NetworkErrorCause.ServerInternal
            throwable?.isConnectionException == true -> NetworkErrorCause.Connection
            throwable?.isConnectionTimeoutException == true -> NetworkErrorCause.ConnectionTimeout
            else -> NetworkErrorCause.Unknown(errorMessage)
        }

        return ResultObject.Error(cause)
    }
}