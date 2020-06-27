package com.github.serhii_v.exchange_rates.presentation.ui.currencies_details

import android.content.Context
import android.content.Intent
import com.github.serhii_v.exchange_rates.presentation.ui.base.BaseActivity

class CurrencyDetailsActivity: BaseActivity() {

    companion object {
        fun intent(context: Context, baseCurrency: String, currency: String): Intent {
            val intent = Intent(context, CurrencyDetailsActivity::class.java)
            intent.putExtras(CurrencyDetailsFragment.bundle(baseCurrency, currency))
            return intent
        }
    }

    override fun fragment() = CurrencyDetailsFragment.get(intent.extras)

}