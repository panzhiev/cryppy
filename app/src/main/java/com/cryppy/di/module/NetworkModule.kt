package com.cryppy.di.module

import android.content.res.Resources
import com.core.data.model.CoinmarketRequestInterceptor
import com.cryppy.BuildConfig
import com.cryppy.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.infomodule.data.api.CoinmarketService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.concurrent.TimeUnit
import javax.inject.Named

private const val COINMARKET_BASE_URL = "coinmarket_base_url"
private const val COINMARKET_AUTHORIZATION_INTERCEPTOR = "coinmarket_authorization_interceptor"
private const val TIMEOUT_SECONDS = 30L

@Module
class NetworkModule {

    @Provides
    @Reusable
    @Named(COINMARKET_BASE_URL)
    // todo provide base url from gradle
    fun provideCoinmarketBaseUrl(resources: Resources): String =
        resources.getString(R.string.coinmarket_base_url)

    @Provides
    @Reusable
    fun provideOkHttpClient(@Named(COINMARKET_AUTHORIZATION_INTERCEPTOR) authorizationInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().also { builder ->
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(authorizationInterceptor)
                .addInterceptor(loggingInterceptor)
                .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        }.build()

    @Provides
    @Named(COINMARKET_AUTHORIZATION_INTERCEPTOR)
    @Reusable
    fun provideAuthorizationInterceptor(): Interceptor =
        CoinmarketRequestInterceptor(BuildConfig.COINMARKET_API_KEY)

    @Provides
    @Reusable
    fun provideGson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat(DateFormat.FULL, DateFormat.FULL)
        .create()

    @Provides
    @Reusable
    fun provideRetrofit(
        @Named(COINMARKET_BASE_URL) baseUrl: String,
        httpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    @Provides
    @Reusable
    fun provideCoinmarketService(retrofit: Retrofit): CoinmarketService = retrofit.create(CoinmarketService::class.java)
}