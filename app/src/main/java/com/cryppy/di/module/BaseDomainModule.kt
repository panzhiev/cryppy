package com.cryppy.di.module

import com.core.domain.CoroutineContextProvider
import com.core.domain.CoroutineContextProviderImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class BaseDomainModule {

    @Provides
    @Reusable
    fun provideCoroutineContextProvider(): CoroutineContextProvider = CoroutineContextProviderImpl()
}