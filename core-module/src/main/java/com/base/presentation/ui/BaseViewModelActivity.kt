package com.base.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.base.presentation.viewmodel.ViewModelFactory
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseViewModelActivity<VIEW_MODEL : ViewModel> : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel: VIEW_MODEL by lazy { injectViewModel() }

    private fun injectViewModel(): VIEW_MODEL =
        ViewModelProvider(this, viewModelFactory).get(getViewModelGenericClass())

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
}