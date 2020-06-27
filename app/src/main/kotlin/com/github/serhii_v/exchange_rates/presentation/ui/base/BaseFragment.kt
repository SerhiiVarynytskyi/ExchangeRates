package com.github.serhii_v.exchange_rates.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.github.serhii_v.exchange_rates.R
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import com.github.serhii_v.exchange_rates.presentation.common.extension.appContext
import com.github.serhii_v.exchange_rates.presentation.common.extension.viewContainer
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<DataBinding : ViewDataBinding, VModel : BaseViewModel> : DaggerFragment() {

    abstract fun layoutId(): Int

    abstract fun buildViewModel(): VModel

    @Inject
    lateinit var log: Logger

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: DataBinding

    val viewModel: VModel by lazy { buildViewModel() }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.detachView()
    }

    open fun initializeView() {

    }

    internal fun notify(@StringRes message: Int) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notify(message: String) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()

    internal fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        appContext?.let {
            val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
            snackBar.setAction(actionText) { _ -> action.invoke() }
            snackBar.setActionTextColor(
                ContextCompat.getColor(it, R.color.colorTextPrimary))
            snackBar.show()
        }
    }

    protected open fun handleState(state: State?): Boolean {
        when (state) {
            is State.Failure -> handleFailure(state)
            else -> return false
        }
        return true
    }

    protected open fun handleFailure(failure: State.Failure?): Boolean {
        when (failure) {
            is State.Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is State.Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is State.Failure.CustomFailure -> renderFailure(failure.exception.message ?: "")
            else -> return false
        }
        return true
    }

    protected open fun renderFailure(@StringRes message: Int) {
        notify(message)
    }

    protected open fun renderFailure(message: String) {
        notify(message)
    }
}