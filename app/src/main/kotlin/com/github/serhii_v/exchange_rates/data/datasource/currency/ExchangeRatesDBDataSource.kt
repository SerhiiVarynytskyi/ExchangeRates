package com.github.serhii_v.exchange_rates.data.datasource.currency

import com.github.serhii_v.exchange_rates.data.datasource.BaseDataSource
import com.github.serhii_v.exchange_rates.domain.entity.Entity

interface ExchangeRatesDBDataSource : BaseDataSource {
    suspend fun saveCurrency(currency: Entity.Currency)
    suspend fun getCurrency(currencyName: String): Entity.Currency?
}