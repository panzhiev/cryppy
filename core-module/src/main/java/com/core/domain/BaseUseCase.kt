package com.core.domain

import com.core.domain.model.ResultObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseUseCase<REQUEST, RESPONSE>(coroutineContextProvider: CoroutineContextProvider) {

    private val job = Job()
    protected val jobs = HashMap<String, Job>()
    private val uiScope = CoroutineScope(coroutineContextProvider.main + job)
    protected lateinit var callback: (RESPONSE) -> Unit

    open fun execute(
        value: REQUEST,
        coroutineScope: CoroutineScope,
        callback: (ResultObject<RESPONSE>) -> Unit
    ) {
        coroutineScope.launch { callback(executeRequest(value)) }
    }

    open fun executeCancelable(
        value: REQUEST,
        callerIdentifier: String,
        coroutineScope: CoroutineScope = uiScope,
        callback: (ResultObject<RESPONSE>) -> Unit
    ) {

        jobs[callerIdentifier]?.cancel()
        jobs[callerIdentifier] = coroutineScope.launch { callback(executeRequest(value)) }
    }

    open fun clear() {}

    open fun clearCancellable(callerIdentifier: String) {}

    abstract suspend fun executeRequest(request: REQUEST): ResultObject<RESPONSE>
}