package com.github.serhii_v.exchange_rates.app.di.module

import com.github.serhii_v.exchange_rates.presentation.ui.SplashActivity
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_details.CurrencyDetailsActivity
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_details.CurrencyDetailsFragment
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_list.ExchangeRatesActivity
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_list.ExchangeRatesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [])
internal abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun currenciesActivity(): ExchangeRatesActivity
    @ContributesAndroidInjector
    internal abstract fun currenciesFragment(): ExchangeRatesFragment

    @ContributesAndroidInjector
    internal abstract fun currencyDetailsActivity(): CurrencyDetailsActivity
    @ContributesAndroidInjector
    internal abstract fun currencyDetailsFragment(): CurrencyDetailsFragment

}