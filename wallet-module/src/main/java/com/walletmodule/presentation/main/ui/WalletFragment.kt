package com.walletmodule.presentation.main.ui

import com.base.presentation.ui.BaseViewModelFragment
import com.walletmodule.R
import com.walletmodule.presentation.main.viewmodel.WalletViewModel

class WalletFragment private constructor() : BaseViewModelFragment<WalletViewModel>() {

    override val layout = R.layout.fragment_wallet

    companion object {
        val TAG: String = this::class.java.name
        fun newInstance() = WalletFragment()
    }
}