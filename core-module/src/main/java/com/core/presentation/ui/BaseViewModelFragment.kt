package com.core.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.core.domain.model.BaseErrorCause
import com.core.presentation.viewmodel.BaseViewModel
import com.core.presentation.viewmodel.ViewModelFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseViewModelFragment<VIEW_MODEL : BaseViewModel> : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel: VIEW_MODEL by lazy { injectViewModel() }

    private fun injectViewModel(): VIEW_MODEL =
        ViewModelProvider(this, viewModelFactory).get(getViewModelGenericClass())

    private val commonNetworkErrorObserver = Observer<BaseErrorCause> { cause ->
        (requireActivity() as BaseActivity).handleCommonNetworkErrors(cause)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelGenericClass(): Class<VIEW_MODEL> {
        try {
            val className =
                (javaClass.genericSuperclass as ParameterizedType)
                    .actualTypeArguments[0]
                    .toString()
                    .split(" ")[1]

            return Class.forName(className) as Class<VIEW_MODEL>
        } catch (e: Exception) {
            e.printStackTrace()
            throw IllegalStateException("Class is not parametrized with generic type!")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.commonNetworkError.observe(this, commonNetworkErrorObserver)
    }
}