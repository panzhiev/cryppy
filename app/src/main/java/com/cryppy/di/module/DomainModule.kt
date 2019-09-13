package com.cryppy.di.module

import com.base.domain.CoroutineContextProvider
import com.base.domain.CoroutineContextProviderImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
abstract class DomainModule {

    @Provides
    @Reusable
    fun provideCoroutineContextProvider(): CoroutineContextProvider = CoroutineContextProviderImpl()
}