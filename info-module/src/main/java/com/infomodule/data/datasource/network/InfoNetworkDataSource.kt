package com.infomodule.data.datasource.network

import com.core.domain.model.ResultObject
import com.infomodule.data.model.Crypto

interface InfoNetworkDataSource {
    fun getCryptos(): ResultObject<List<Crypto>>
}