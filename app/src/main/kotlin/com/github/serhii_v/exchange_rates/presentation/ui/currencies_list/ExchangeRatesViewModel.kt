package com.github.serhii_v.exchange_rates.presentation.ui.currencies_list

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.domain.entity.Entity
import com.github.serhii_v.exchange_rates.domain.repository.currency.ExchangeRatesRepository
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseFragment
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExchangeRatesViewModel @Inject
constructor(private val repository: ExchangeRatesRepository) : BaseViewModel() {

    var onItemClick: ((baseCurrency: String, Entity.Rate) -> Unit)? = null
    val currency = MutableLiveData<Entity.Currency>()
    val exchangeRateAdapter = MutableLiveData<ExchangeRatesAdapter>(ExchangeRatesAdapter("", emptyList(), null))

    override fun attachView(view: BaseFragment<out ViewDataBinding, out BaseViewModel>) {
        super.attachView(view)

        currency.value.let {
            if (it == null) getExchangeRateData() else getExchangeRateData(it.currencyName)
        }
    }

    fun onBaseCurrencyChanged(position: Int) {
        currency.value?.let {
            if (it.currencyName != it.rates[position].currencyName) {
                getExchangeRateData(it.rates[position].currencyName)
            }
        }
    }

    private fun getExchangeRateData(baseCurrency: String? = null) {
        handleState(State.Loading)
        viewModelScope.launch {
            repository.getExchangeRate(baseCurrency).either(
                onError = { handleState(it) },
                onSuccess = { setCurrentCurrency(it) }
            )
        }
    }

    private fun setCurrentCurrency(currency: Entity.Currency) {
        this.currency.value = currency
        if (currency.rates.isEmpty()) {
            handleState(State.NoData)
        } else {
            handleState(State.HasData)
        }
        this.exchangeRateAdapter.value = ExchangeRatesAdapter(currency.currencyName, currency.rates, onItemClick)
    }
}