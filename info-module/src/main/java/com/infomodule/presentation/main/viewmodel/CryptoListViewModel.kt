package com.infomodule.presentation.main.viewmodel

import androidx.lifecycle.viewModelScope
import com.core.domain.model.ResultObject
import com.core.presentation.viewmodel.BaseViewModel
import com.infomodule.data.model.Crypto
import com.infomodule.domain.CryptoListUseCase
import javax.inject.Inject

class CryptoListViewModel
@Inject constructor(private val cryptoListUseCase: CryptoListUseCase) : BaseViewModel() {

    fun getCryptos() {
        cryptoListUseCase.execute(Unit, viewModelScope) { result ->
            when (result) {
                is ResultObject.Success -> handleCryptoListSuccess(result.data)
                else -> handleCommonErrors((result as ResultObject.Error).cause)
                { error -> handleCryptoListError(error) }
            }
        }
    }

    private fun handleCryptoListSuccess(data: List<Crypto>) {

    }

    private fun handleCryptoListError(error: String) {

    }
}