package com.core.presentation.ui

import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import com.core.domain.model.BaseErrorCause
import com.core.domain.model.NetworkErrorCause
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    open var containerId: Int = -1

    fun addFragmentToContainer(
        fragment: Fragment,
        fragmentTag: String,
        @AnimRes fromAnim: Int = -1,
        @AnimRes toAnim: Int = -1
    ) {

        if (containerId == -1) {
            throw Exception("You need to provide valid containerId")
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (fromAnim != -1 && toAnim != -1) {
            fragmentTransaction.setCustomAnimations(fromAnim, toAnim)
        }

        fragmentTransaction
            .replace(containerId, fragment, fragmentTag)
            .commit()
    }

    fun handleCommonNetworkErrors(cause: BaseErrorCause) {
        var message = ""
        when (cause) {
            is NetworkErrorCause.Unauthorized -> message = "Unauthorized"
            is NetworkErrorCause.Connection -> message = "Connection Error"
            is NetworkErrorCause.ConnectionTimeout -> message = "Connection Timeout"
            is NetworkErrorCause.ServerInternal -> message = "Server Internal Error"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}