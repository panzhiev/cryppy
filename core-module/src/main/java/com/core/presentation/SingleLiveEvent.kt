package com.core.presentation

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val pendingValue = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (!hasObservers()) {
            super.observe(owner, Observer<T> { t ->
                if (pendingValue.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            })
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        pendingValue.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}