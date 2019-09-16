package com.core.domain

import com.core.domain.model.ResultObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BackgroundExecuteUseCase<REQUEST, RESPONSE> constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseUseCase<REQUEST, RESPONSE>(coroutineContextProvider) {

    override fun execute(
        value: REQUEST,
        coroutineScope: CoroutineScope,
        callback: (ResultObject<RESPONSE>) -> Unit
    ) {
        coroutineScope.launch {
            val result = withContext(coroutineContextProvider.io) {
                executeRequest(value)
            }

            callback(result)
        }
    }

    override fun executeCancelable(
        value: REQUEST,
        callerIdentifier: String,
        coroutineScope: CoroutineScope,
        callback: (ResultObject<RESPONSE>) -> Unit
    ) {

        jobs[callerIdentifier]?.cancel()
        jobs[callerIdentifier] = coroutineScope.launch {
            val result = withContext(coroutineContextProvider.io) {
                executeRequest(value)
            }
            callback(result)
        }
    }
}