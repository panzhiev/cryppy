package com.cryppy.di.module

import com.infomodule.data.datasource.network.InfoNetworkDataSource
import com.infomodule.data.datasource.network.InfoNetworkDataSourceImpl
import com.infomodule.data.repository.InfoRepository
import com.infomodule.data.repository.InfoRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BaseDataModule {

    @Binds
    @Singleton
    abstract fun provideInfoRepository(repository: InfoRepositoryImpl): InfoRepository

    @Binds
    @Singleton
    abstract fun provideInfoNetworkDataSource(dataSource: InfoNetworkDataSourceImpl): InfoNetworkDataSource
}