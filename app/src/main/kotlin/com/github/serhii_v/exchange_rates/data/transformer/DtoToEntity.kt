package com.github.serhii_v.exchange_rates.data.transformer

import com.github.serhii_v.exchange_rates.data.api.ExchangeRatesApi
import com.github.serhii_v.exchange_rates.domain.entity.Entity
import java.util.*


fun ExchangeRatesApi.Dto.Currency.map() = Entity.Currency(
    currencyName = this.base,
    date = Date(),
    rates = rates.map { (key, value) -> Entity.Rate(key, value, date.toDate()) }
)

fun ExchangeRatesApi.Dto.RateOfDay.map() = Entity.Rate(
    currencyName = this.currency,
    currencyValue = this.value,
    date = this.date.toDate()
)

fun ExchangeRatesApi.Dto.CurrencyDetails.map() = Entity.CurrencyDetails(
    currencyName = this.base,
    startDate = this.startDate.toDate(),
    endDate = this.endDate.toDate(),
    rates = this.rates.list.map { rateOfDay -> rateOfDay.map() }
)
