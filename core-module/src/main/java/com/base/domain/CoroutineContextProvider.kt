package com.base.domain

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {

    val main: CoroutineContext
    val io: CoroutineContext
}