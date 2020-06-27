package com.github.serhii_v.exchange_rates.domain.entity

import com.github.serhii_v.exchange_rates.presentation.common.extension.toMoneyValue
import java.util.*

sealed class Entity {

    data class Rate(val currencyName: String, val currencyValue: Float, val date: Date?) {
        fun currencyMoneyValue(): String {
            return this.currencyValue.toMoneyValue()
        }
    }

    data class Currency(val currencyName: String,
                        val date: Date,
                        val rates: List<Rate>)

    data class CurrencyDetails(val currencyName: String,
                               val startDate: Date?,
                               val endDate: Date?,
                               val rates: List<Rate>)

}
