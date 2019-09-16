package com.core.data.model

import okhttp3.Interceptor
import okhttp3.Response

class CoinmarketRequestInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain
            .request()
            .newBuilder()
            .addHeader("X-CMC_PRO_API_KEY", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}