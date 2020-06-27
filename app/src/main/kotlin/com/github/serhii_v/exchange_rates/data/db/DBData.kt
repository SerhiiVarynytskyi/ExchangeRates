package com.github.serhii_v.exchange_rates.data.db

import androidx.room.*
import java.util.*

sealed class DBData {

    @Entity(tableName = "currency")
    data class Currency(
        @PrimaryKey
        @ColumnInfo(name = "currencyName") val currencyName: String,
        @ColumnInfo(name = "date") val date: Date
    ): DBData()

    @Entity(
        tableName = "rate",
        foreignKeys = [(ForeignKey(
            entity = Currency::class,
            parentColumns = [("currencyName")],
            childColumns = [("baseCurrencyName")],
            onDelete = ForeignKey.CASCADE
        ))],
        indices = [Index("baseCurrencyName")]
    )
    data class Rate(
        @PrimaryKey
        @ColumnInfo(name = "rateCurrencyName") val rateCurrencyName: String,
        @ColumnInfo(name = "baseCurrencyName") val baseCurrencyName: String,
        @ColumnInfo(name = "currencyValue") val currencyValue: Float,
        @ColumnInfo(name = "date") val date: Date? = null
    ): DBData()



    data class CurrencyWithRate(
        @Embedded
        val currency: Currency,
        @Relation(parentColumn = "currencyName", entityColumn = "baseCurrencyName", entity = Rate::class)
        val rates: List<Rate>
    ): DBData()

}