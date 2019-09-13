package com.infomodule.data.repository

import com.base.domain.model.ResultObject
import com.infomodule.data.datasource.network.InfoNetworkDataSource
import com.infomodule.data.model.Crypto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InfoRepositoryImpl
@Inject constructor(val infoNetworkDataSource: InfoNetworkDataSource) : InfoRepository {

    override fun getCryptos(): ResultObject<List<Crypto>> {
        return infoNetworkDataSource.getCryptos()
    }
}