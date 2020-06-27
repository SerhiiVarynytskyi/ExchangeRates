package com.github.serhii_v.exchange_rates.presentation.common.navigation

import android.content.Context
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_details.CurrencyDetailsActivity
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_list.ExchangeRatesActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun showMain(context: Context) {
        context.startActivity(ExchangeRatesActivity.intent(context))
    }

    fun showCurrencyDetails(context: Context, baseCurrency: String, currency: String) {
        context.startActivity(CurrencyDetailsActivity.intent(context, baseCurrency, currency))
    }

}