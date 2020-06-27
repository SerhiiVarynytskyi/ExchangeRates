package com.github.serhii_v.exchange_rates.presentation.ui.currencies_details

import androidx.lifecycle.MutableLiveData
import com.github.serhii_v.exchange_rates.R
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.domain.entity.Entity
import com.github.serhii_v.exchange_rates.domain.repository.currency.ExchangeRatesRepository
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyDetailsViewModel @Inject constructor(
    private val repository: ExchangeRatesRepository
) : BaseViewModel() {

    val title = MutableLiveData<String>()
    val currencyDetails = MutableLiveData<Entity.CurrencyDetails>()

    fun setCurrency(baseCurrency: String, currency: String) {
        title.value = "$baseCurrency / $currency ${view?.getString(R.string.last_week)}"

        handleState(State.Loading)
        viewModelScope.launch {
            repository.getCurrencyDetails(baseCurrency, currency).either(
                onError = { handleState(it) },
                onSuccess = { setCurrencyDetails(it) }
            )
        }
    }

    private fun setCurrencyDetails(currencyDetails: Entity.CurrencyDetails) {
        if (currencyDetails.rates.isNullOrEmpty()) {
            handleState(State.NoData)
        } else {
            handleState(State.HasData)
        }
        this.currencyDetails.value = currencyDetails
    }
}