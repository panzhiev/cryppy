package com.cryppy.di.module

import androidx.lifecycle.ViewModel
import com.accountmodule.presentation.main.viewmodel.AccountViewModel
import com.core.presentation.viewmodel.ViewModelFactory
import com.core.presentation.viewmodel.ViewModelFactoryImpl
import com.cryppy.di.annotation.ViewModelKey
import com.infomodule.presentation.main.viewmodel.CryptoListViewModel
import com.walletmodule.presentation.main.viewmodel.WalletViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class PresentationModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactoryImpl): ViewModelFactory

    @Binds
    @IntoMap
    @ViewModelKey(CryptoListViewModel::class)
    abstract fun bindCryptoListViewModel(viewModel: CryptoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WalletViewModel::class)
    abstract fun bindWalletViewModel(viewModel: WalletViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel
}