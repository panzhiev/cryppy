package com.base.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BackgroundExecuteUseCase<REQUEST, RESPONSE> constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseUseCase<REQUEST, RESPONSE>(coroutineContextProvider) {

    override suspend fun execute(
        value: REQUEST,
        callback: (RESPONSE) -> Unit,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            val result = withContext(coroutineContextProvider.io) {
                executeRequest(value)
            }

            callback(result)
        }
    }

    override suspend fun executeCancelable(
        value: REQUEST,
        callerIdentifier: String,
        callback: (RESPONSE) -> Unit,
        coroutineScope: CoroutineScope
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