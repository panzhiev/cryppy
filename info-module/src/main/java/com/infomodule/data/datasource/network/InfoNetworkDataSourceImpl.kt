package com.infomodule.data.datasource.network

import com.base.domain.model.ResultObject
import com.infomodule.data.model.Crypto

class InfoNetworkDataSourceImpl : InfoNetworkDataSource{

    override fun getCryptos(): ResultObject<List<Crypto>> {
        return ResultObject.Success(arrayListOf())
    }
}