package com.github.serhii_v.exchange_rates.data.repository.currency

import com.github.serhii_v.exchange_rates.data.datasource.currency.ExchangeRatesApiDataSource
import com.github.serhii_v.exchange_rates.data.datasource.currency.ExchangeRatesDBDataSource
import com.github.serhii_v.exchange_rates.data.local.Preferences
import com.github.serhii_v.exchange_rates.data.repository.BaseRepositoryImpl
import com.github.serhii_v.exchange_rates.data.transformer.is10MinutesPassed
import com.github.serhii_v.exchange_rates.domain.common.Constants.Companion.DEFAULT_CURRENCY
import com.github.serhii_v.exchange_rates.domain.common.Result
import com.github.serhii_v.exchange_rates.domain.common.State
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import com.github.serhii_v.exchange_rates.domain.common.platform.INetworkHandler
import com.github.serhii_v.exchange_rates.domain.entity.Entity
import com.github.serhii_v.exchange_rates.domain.repository.currency.ExchangeRatesRepository
import java.util.*

class ExchangeRatesRepositoryImpl(
    private val networkHandler: INetworkHandler,
    private val apiSource: ExchangeRatesApiDataSource,
    private val localData: ExchangeRatesDBDataSource,
    private val pref: Preferences,
    private val log: Logger
) : BaseRepositoryImpl(), ExchangeRatesRepository {

    override suspend fun getExchangeRate(baseCurrency: String?): Result<Entity.Currency, State.Failure> {
        val currencyString = baseCurrency ?: (pref.getCurrency() ?: DEFAULT_CURRENCY)
        val lastRequestTime = pref.getLastRequestTime()
        if (lastRequestTime != null && !lastRequestTime.is10MinutesPassed()) {
            localData.getCurrency(currencyString)?.let {
                if (log.isDebug) log.v("Result DB")
                return Result.Data(it)
            }
        }
        if (!networkHandler.isConnectedToInternet()) {
            return Result.Error(State.Failure.NotInternetConnected)
        }
        return apiSource.getExchangeRate(currencyString).also {
            it.either(
                onError = {},
                onSuccess = { currency ->
                    if (log.isDebug) log.v("Result API")
                    pref.setCurrency(currencyString)
                    pref.setLastRequestTime(Date())
                    localData.saveCurrency(currency)
                }
            )
        }
    }

    override suspend fun getCurrencyDetails(
        baseCurrency: String,
        currency: String
    ): Result<Entity.CurrencyDetails, State.Failure> {
        return apiSource.getCurrencyDetails(baseCurrency, currency)
    }

}