package com.github.serhii_v.exchange_rates.app.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_details.CurrencyDetailsViewModel
import com.github.serhii_v.exchange_rates.presentation.ui.currencies_list.ExchangeRatesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ExchangeRatesViewModel::class)
    abstract fun bindExchangeRatesViewModel(viewModel: ExchangeRatesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyDetailsViewModel::class)
    abstract fun bindCurrencyDetailsViewModel(viewModel: CurrencyDetailsViewModel): ViewModel
}