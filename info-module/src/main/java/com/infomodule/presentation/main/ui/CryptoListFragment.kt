package com.infomodule.presentation.main.ui

import android.os.Bundle
import android.view.View
import com.base.presentation.ui.BaseViewModelFragment
import com.info.R
import com.infomodule.presentation.main.viewmodel.CryptoListViewModel

class CryptoListFragment private constructor() : BaseViewModelFragment<CryptoListViewModel>() {

    override val layout = R.layout.fragment_info

    companion object {
        val TAG: String = this::class.java.name
        fun newInstance() = CryptoListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}