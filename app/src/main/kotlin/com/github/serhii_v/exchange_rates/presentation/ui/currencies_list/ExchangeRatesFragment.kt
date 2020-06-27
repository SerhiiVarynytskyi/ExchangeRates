package com.github.serhii_v.exchange_rates.presentation.ui.currencies_list

import android.view.View
import android.widget.AdapterView
import com.github.serhii_v.exchange_rates.R
import com.github.serhii_v.exchange_rates.databinding.FragmentExchangeRatesBinding
import com.github.serhii_v.exchange_rates.presentation.common.extension.observeState
import com.github.serhii_v.exchange_rates.presentation.common.extension.viewModel
import com.github.serhii_v.exchange_rates.presentation.common.navigation.Navigator
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_exchange_rates.*
import javax.inject.Inject

class ExchangeRatesFragment : BaseFragment<FragmentExchangeRatesBinding, ExchangeRatesViewModel>(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = ExchangeRatesFragment()
    }

    @Inject
    internal lateinit var navigator: Navigator

    override fun layoutId() = R.layout.fragment_exchange_rates

    override fun buildViewModel(): ExchangeRatesViewModel {
        return viewModel(viewModelFactory) {
            observeState(state) { handleState(it) }
        }
    }

    override fun initializeView() {
        super.initializeView()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
        viewModel.onItemClick = { baseCurrency, rate ->
            context?.let {
                navigator.showCurrencyDetails(it, baseCurrency, rate.currencyName)
            }
        }
        baseCurrencySpinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.onBaseCurrencyChanged(position)
    }
}