package com.cryppy.di.module

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class BasePresentationModule {

    @Provides
    @Reusable
    fun provideResources(context: Context): Resources = context.resources
}