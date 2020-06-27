package com.github.serhii_v.exchange_rates.presentation.ui.currencies_list

import android.content.Context
import android.content.Intent
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseActivity

class ExchangeRatesActivity : BaseActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, ExchangeRatesActivity::class.java)
    }

    override fun fragment() = ExchangeRatesFragment.newInstance()

}