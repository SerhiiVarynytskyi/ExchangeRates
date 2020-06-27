package com.github.serhii_v.exchange_rates.data.transformer

import com.github.serhii_v.exchange_rates.data.db.DBData
import com.github.serhii_v.exchange_rates.domain.entity.Entity

fun Entity.Rate.map(baseCurrencyName: String) = DBData.Rate(
    baseCurrencyName = baseCurrencyName,
    rateCurrencyName = this.currencyName,
    currencyValue = this.currencyValue,
    date = this.date
)

fun Entity.Currency.map() = DBData.CurrencyWithRate(
    currency = DBData.Currency(currencyName = this.currencyName, date = this.date),
    rates = this.rates.map { it.map(this.currencyName) }
)
