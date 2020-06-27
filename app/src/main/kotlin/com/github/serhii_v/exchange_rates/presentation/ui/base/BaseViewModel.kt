package com.github.serhii_v.exchange_rates.presentation.ui.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var log: Logger

    private var viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    protected var view: BaseFragment<out ViewDataBinding, out BaseViewModel>? = null

    open val state: LiveData<State> = MutableLiveData()

    protected fun handleState(s: State) {
        state.let {
            if (it is MutableLiveData<State>) {
                it.value = s
            } else {
                throw RuntimeException("state LiveData is not MutableLiveData")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    open fun attachView(view: BaseFragment<out ViewDataBinding, out BaseViewModel>) {
        this.view = view
    }

    open fun detachView() {
        this.view = null
    }
}