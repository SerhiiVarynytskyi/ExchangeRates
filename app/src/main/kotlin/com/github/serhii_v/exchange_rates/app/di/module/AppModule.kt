package com.github.serhii_v.exchange_rates.app.di.module

import android.content.Context
import com.github.serhii_v.exchange_rates.app.App
import dagger.Module
import dagger.Provides

@Module(includes = [NetModule::class, DatabaseModule::class, LoggerModule::class])
class AppModule {

    @Provides
    fun providesContext(application: App): Context {
        return application.applicationContext
    }

}