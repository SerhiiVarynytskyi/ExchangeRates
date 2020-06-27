package com.github.serhii_v.exchange_rates.app.di.module

import android.content.Context
import com.github.serhii_v.exchange_rates.data.api.ExchangeRatesApi
import com.github.serhii_v.exchange_rates.data.datasource.currency.ExchangeRatesApiDataSource
import com.github.serhii_v.exchange_rates.data.datasource.currency.ExchangeRatesApiDataSourceImpl
import com.github.serhii_v.exchange_rates.data.datasource.currency.ExchangeRatesDBDataSource
import com.github.serhii_v.exchange_rates.data.datasource.currency.ExchangeRatesDBDataSourceImpl
import com.github.serhii_v.exchange_rates.data.db.currency.ExchangeRatesDao
import com.github.serhii_v.exchange_rates.data.local.Preferences
import com.github.serhii_v.exchange_rates.data.repository.currency.ExchangeRatesRepositoryImpl
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import com.github.serhii_v.exchange_rates.domain.repository.currency.ExchangeRatesRepository
import com.github.serhii_v.exchange_rates.presentation.common.platform.NetworkHandlerImpl
import dagger.Module
import dagger.Provides

@Module
class ExchangeRatesModule {

    @Provides
    fun provideDatabaseSource(exchangeRatesDao: ExchangeRatesDao, log: Logger): ExchangeRatesDBDataSource =
        ExchangeRatesDBDataSourceImpl(exchangeRatesDao)

    @Provides
    fun provideApiSource(api: ExchangeRatesApi, log: Logger): ExchangeRatesApiDataSource =
        ExchangeRatesApiDataSourceImpl(api, log)

    @Provides
    fun provideExchangeRatesRepository(
        context: Context,
        apiSource: ExchangeRatesApiDataSource,
        dbSource: ExchangeRatesDBDataSource,
        pref: Preferences,
        log: Logger
    ): ExchangeRatesRepository = ExchangeRatesRepositoryImpl(NetworkHandlerImpl(context), apiSource, dbSource, pref, log)

    @Provides
    fun providePreferences(context: Context): Preferences = Preferences(context)
}