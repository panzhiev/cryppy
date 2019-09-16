package com.infomodule.data.repository

import com.core.domain.model.ResultObject
import com.infomodule.data.model.Crypto

interface InfoRepository {

    fun getCryptos(): ResultObject<List<Crypto>>
}