package com.github.serhii_v.exchange_rates.app.di.module

import com.github.serhii_v.exchange_rates.app.logger.LoggerImpl
import com.github.serhii_v.exchange_rates.domain.common.logger.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LoggerModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()

}