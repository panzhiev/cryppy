package com.infomodule.data.api

import com.core.data.model.BaseCoinmarketResponse
import com.infomodule.data.model.Crypto
import retrofit2.Call
import retrofit2.http.GET

private const val CRYPTOCURRENCIES_PATH = "v1/cryptocurrency/listings/latest"

interface CoinmarketService {

    @GET(CRYPTOCURRENCIES_PATH)
    fun getCryptos(): Call<BaseCoinmarketResponse<List<Crypto>>>

}