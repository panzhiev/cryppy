package com.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactoryImpl
@Inject constructor(private val viewModelProviders: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = viewModelProviders[modelClass]
        if (creator == null) {
            for ((key, value) in viewModelProviders.entries) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}