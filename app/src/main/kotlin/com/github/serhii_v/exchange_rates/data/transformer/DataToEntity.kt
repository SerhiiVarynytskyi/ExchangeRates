package com.github.serhii_v.exchange_rates.data.transformer

import com.github.serhii_v.exchange_rates.data.db.DBData
import com.github.serhii_v.exchange_rates.domain.entity.Entity

fun DBData.Rate.map() = Entity.Rate(
    currencyName = this.rateCurrencyName,
    currencyValue = this.currencyValue,
    date = this.date
)

fun DBData.CurrencyWithRate.map() = Entity.Currency(
    currencyName = this.currency.currencyName,
    date = this.currency.date,
    rates = this.rates.map { it.map() }
)
