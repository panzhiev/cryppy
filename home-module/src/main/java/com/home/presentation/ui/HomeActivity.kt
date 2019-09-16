package com.home.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.accountmodule.presentation.main.ui.AccountFragment
import com.core.presentation.ui.BaseActivity
import com.home.R
import com.infomodule.presentation.main.ui.CryptoListFragment
import com.walletmodule.presentation.main.ui.WalletFragment
import kotlinx.android.synthetic.main.activity_home.home_bottom_navigation_bar as bottomBar

class HomeActivity : BaseActivity() {

    override var containerId: Int = R.id.main_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupBottomBar()
    }

    private fun setupBottomBar() {
        bottomBar.setOnNavigationItemSelectedListener {
            navigate(it.itemId)
            true
        }

        intent?.extras?.getInt(ARG_TAB)?.let {
            navigate(it)
        } ?: run {
            navigate(R.id.nav_info)
        }
    }

    private fun navigate(menuId: Int) {
        lateinit var fragment: Fragment
        lateinit var tag: String
        when (menuId) {
            R.id.nav_info -> {
                fragment = CryptoListFragment.newInstance()
                tag = CryptoListFragment.TAG
            }
            R.id.nav_wallet -> {
                fragment = WalletFragment.newInstance()
                tag = WalletFragment.TAG
            }
            R.id.nav_account -> {
                fragment = AccountFragment.newInstance()
                tag = AccountFragment.TAG
            }
            else -> throw Exception("Wrong tab number. Tab number: $menuId")
        }
        addFragmentToContainer(fragment, tag)
    }

    companion object {
        private const val ARG_TAB = "ARG_TAB"
    }
}