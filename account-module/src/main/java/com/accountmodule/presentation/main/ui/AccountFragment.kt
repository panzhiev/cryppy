package com.accountmodule.presentation.main.ui

import com.accountmodule.R
import com.accountmodule.presentation.main.viewmodel.AccountViewModel
import com.base.presentation.ui.BaseViewModelFragment

class AccountFragment private constructor() : BaseViewModelFragment<AccountViewModel>() {

    override val layout = R.layout.fragment_account

    companion object {
        val TAG: String = this::class.java.name
        fun newInstance() = AccountFragment()
    }
}