package com.github.serhii_v.exchange_rates.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.serhii_v.exchange_rates.data.db.currency.ExchangeRatesDao
import com.github.serhii_v.exchange_rates.data.transformer.DateConverter

@Database(
    entities = [
        DBData.Rate::class,
        DBData.Currency::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getExchangeRatesDao(): ExchangeRatesDao

}