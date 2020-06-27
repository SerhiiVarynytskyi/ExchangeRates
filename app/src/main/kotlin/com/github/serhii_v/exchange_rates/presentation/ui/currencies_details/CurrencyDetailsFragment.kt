package com.github.serhii_v.exchange_rates.presentation.ui.currencies_details

import android.os.Bundle
import com.github.serhii_v.exchange_rates.R
import com.github.serhii_v.exchange_rates.databinding.FragmentCurrencyDetailsBinding
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.presentation.common.extension.observe
import com.github.serhii_v.exchange_rates.presentation.common.extension.viewModel
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseActivity
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseFragment


class CurrencyDetailsFragment : BaseFragment<FragmentCurrencyDetailsBinding, CurrencyDetailsViewModel>() {

    companion object {
        private const val PARAM_CURRENCY = "currency"
        private const val PARAM_BASE_CURRENCY = "baseCurrency"

        fun bundle(baseCurrency: String, currency: String): Bundle {
            return Bundle().apply {
                putString(PARAM_CURRENCY, currency)
                putString(PARAM_BASE_CURRENCY, baseCurrency)
            }
        }

        fun get(bundle: Bundle?) : CurrencyDetailsFragment {
            return CurrencyDetailsFragment().apply { arguments = bundle }
        }
    }

    private val currency: String
        get() = arguments?.getString(PARAM_CURRENCY) ?: ""
    private val baseCurrency: String
        get() = arguments?.getString(PARAM_BASE_CURRENCY) ?: ""


    override fun layoutId() = R.layout.fragment_currency_details

    override fun buildViewModel(): CurrencyDetailsViewModel {
        return viewModel(viewModelFactory) {
            observe(title) {
                (activity as? BaseActivity)?.supportActionBar?.title = it
            }
        }
    }

    override fun initializeView() {
        super.initializeView()
        viewModel.setCurrency(baseCurrency, currency)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun handleState(state: State?): Boolean {
        if (!super.handleState(state)) {
            when (state) {
                is State.NoData -> notify(R.string.no_exchange_rate_data)
                else -> return false
            }
        }
        return true
    }
}