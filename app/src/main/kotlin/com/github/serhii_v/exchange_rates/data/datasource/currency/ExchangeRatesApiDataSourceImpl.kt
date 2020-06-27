package com.github.serhii_v.exchange_rates.data.datasource.currency

import com.github.serhii_v.exchange_rates.data.api.ExchangeRatesApi
import com.github.serhii_v.exchange_rates.data.transformer.getDateBeforeWeek
import com.github.serhii_v.exchange_rates.data.transformer.map
import com.github.serhii_v.exchange_rates.data.transformer.toApiString
import com.github.serhii_v.exchange_rates.domain.common.Result
import com.github.serhii_v.exchange_rates.domain.common.State.Failure
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import com.github.serhii_v.exchange_rates.domain.entity.Entity.Currency
import com.github.serhii_v.exchange_rates.domain.entity.Entity.CurrencyDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.util.*

class ExchangeRatesApiDataSourceImpl(private val api: ExchangeRatesApi,
                                     private val log: Logger) : ExchangeRatesApiDataSource {

    override suspend fun getExchangeRate(baseCurrency: String): Result<Currency, Failure> =
        withContext(Dispatchers.IO) {
            requestExecute(api.getExchangeRate(baseCurrency)) { item -> item.map() }
        }

    override suspend fun getCurrencyDetails(baseCurrency: String,
                                            currency: String): Result<CurrencyDetails, Failure> =
        withContext(Dispatchers.IO) {
            val endDate = Date().toApiString()
            val startDate = Date().getDateBeforeWeek().toApiString()
            requestExecute(api.getCurrencyDetails(baseCurrency, currency, startDate, endDate)) { item ->
                item.map()
            }
        }

    private fun <T, R> requestExecute(call: Call<T>, transform: (T) -> R): Result<R, Failure> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> {
                    val body = response.body()
                    if (body != null) {
                        Result.Data(transform(body))
                    } else {
                        Result.Error(Failure.ServerError)
                    }
                }
                false -> Result.Error(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            if (log.isDebug) {
                log.e(exception, "message= ${exception.message}")
            }
            Result.Error(Failure.CustomFailure(exception))
        }
    }
}