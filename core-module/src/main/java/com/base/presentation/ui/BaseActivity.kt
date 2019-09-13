package com.base.presentation.ui

import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
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

//    fun addFragmentToContainerWithAnimations(
//        fragment: Fragment,
//        fragmentTag: String
//    ) {
//
//        if (containerId == -1) {
//            throw Exception("You need to provide valid containerId")
//        }
//
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//
//        fragmentTransaction
//            .setCustomAnimations(fromAnim, toAnim)
//            .replace(containerId, fragment, fragmentTag)
//            .commit()
//    }
}