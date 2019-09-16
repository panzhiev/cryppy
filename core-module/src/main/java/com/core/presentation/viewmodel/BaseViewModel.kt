package com.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.core.domain.model.BaseErrorCause
import com.core.domain.model.NetworkErrorCause
import com.core.presentation.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val commonNetworkError = SingleLiveEvent<BaseErrorCause>()

    protected fun handleCommonErrors(
        cause: BaseErrorCause,
        specificErrorHandler: (String) -> Unit
    ) {
        //todo error handling
        if (cause is NetworkErrorCause.Unknown) {
            specificErrorHandler(cause.errorMessage)
        } else {
            commonNetworkError.value = cause
        }
    }
}