package com.base.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseUseCase<REQUEST, RESPONSE>(coroutineContextProvider: CoroutineContextProvider) {

    protected val jobs = HashMap<String, Job>()

    private val job = Job()
    private val uiScope = CoroutineScope(coroutineContextProvider.main + job)

    protected lateinit var callback: (RESPONSE) -> Unit

    protected open suspend fun execute(
        value: REQUEST, callback: (RESPONSE) -> Unit,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch { callback(executeRequest(value)) }
    }

    protected open suspend fun executeCancelable(
        value: REQUEST,
        callerIdentifier: String,
        callback: (RESPONSE) -> Unit,
        coroutineScope: CoroutineScope = uiScope
    ) {

        jobs[callerIdentifier]?.cancel()
        jobs[callerIdentifier] = coroutineScope.launch { callback(executeRequest(value)) }
    }

    open fun clear() {}

    open fun clearCancellable(callerIdentifier: String) {}

    abstract suspend fun executeRequest(request: REQUEST): RESPONSE
}