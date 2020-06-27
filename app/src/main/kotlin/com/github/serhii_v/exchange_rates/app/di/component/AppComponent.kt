package com.github.serhii_v.exchange_rates.app.di.component

import com.github.serhii_v.exchange_rates.app.App
import com.github.serhii_v.exchange_rates.app.di.module.AppModule
import com.github.serhii_v.exchange_rates.app.di.module.ExchangeRatesModule
import com.github.serhii_v.exchange_rates.app.di.module.MainModule
import com.github.serhii_v.exchange_rates.app.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainModule::class,
        ViewModelModule::class,
        ExchangeRatesModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}