package com.github.serhii_v.exchange_rates.domain.repository.currency

import com.github.serhii_v.exchange_rates.domain.common.Result
import com.github.serhii_v.exchange_rates.domain.common.State.Failure
import com.github.serhii_v.exchange_rates.domain.entity.Entity.Currency
import com.github.serhii_v.exchange_rates.domain.entity.Entity.CurrencyDetails
import com.github.serhii_v.exchange_rates.domain.repository.BaseRepository

interface ExchangeRatesRepository : BaseRepository {

    suspend fun getExchangeRate(baseCurrency: String?): Result<Currency, Failure>
    suspend fun getCurrencyDetails(baseCurrency: String, currency: String): Result<CurrencyDetails, Failure>

}