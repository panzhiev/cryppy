package com.infomodule.domain

import com.core.domain.BackgroundExecuteUseCase
import com.core.domain.CoroutineContextProvider
import com.core.domain.model.ResultObject
import com.infomodule.data.model.Crypto
import com.infomodule.data.repository.InfoRepository
import javax.inject.Inject

class CryptoListUseCase
@Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val repository: InfoRepository
) :
    BackgroundExecuteUseCase<Unit, List<Crypto>>(coroutineContextProvider) {
    override suspend fun executeRequest(request: Unit): ResultObject<List<Crypto>> =
        repository.getCryptos()
}