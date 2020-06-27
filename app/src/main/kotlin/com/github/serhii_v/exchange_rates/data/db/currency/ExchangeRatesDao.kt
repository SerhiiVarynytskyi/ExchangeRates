package com.github.serhii_v.exchange_rates.data.db.currency

import androidx.room.*
import com.github.serhii_v.exchange_rates.data.db.DBData.*

@Dao
abstract class ExchangeRatesDao {

    @Query("DELETE FROM currency")
    abstract suspend fun deleteCurrency()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(rate: Rate): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(currency: Currency): Long

    @Transaction
    open suspend fun save(currencyWithRate: CurrencyWithRate) {
        insert(currencyWithRate.currency)
        for (rate in currencyWithRate.rates) {
            insert(rate)
        }
    }

    @Transaction
    @Query("SELECT * FROM currency WHERE currencyName = :currencyName")
    abstract suspend fun getCurrency(currencyName: String): CurrencyWithRate?

}