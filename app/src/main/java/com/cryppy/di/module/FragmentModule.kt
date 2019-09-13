package com.cryppy.di.module

import com.accountmodule.presentation.main.ui.AccountFragment
import com.infomodule.presentation.main.ui.CryptoListFragment
import com.walletmodule.presentation.main.ui.WalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): CryptoListFragment

    @ContributesAndroidInjector
    abstract fun contributeWalletFragment(): WalletFragment

    @ContributesAndroidInjector
    abstract fun contributeAccountFragment(): AccountFragment
}