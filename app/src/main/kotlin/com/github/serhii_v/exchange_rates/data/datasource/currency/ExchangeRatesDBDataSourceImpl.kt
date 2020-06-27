package com.github.serhii_v.exchange_rates.data.datasource.currency

import com.github.serhii_v.exchange_rates.data.db.currency.ExchangeRatesDao
import com.github.serhii_v.exchange_rates.data.transformer.map
import com.github.serhii_v.exchange_rates.domain.entity.Entity

class ExchangeRatesDBDataSourceImpl(private val exchangeRatesDao: ExchangeRatesDao) : ExchangeRatesDBDataSource {

    override suspend fun saveCurrency(currency: Entity.Currency) {
        exchangeRatesDao.deleteCurrency()
        exchangeRatesDao.save(currency.map())
    }

    override suspend fun getCurrency(currencyName: String): Entity.Currency? {
        return exchangeRatesDao.getCurrency(currencyName)?.map()
    }

}