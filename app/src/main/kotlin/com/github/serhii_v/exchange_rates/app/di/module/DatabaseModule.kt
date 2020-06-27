package com.github.serhii_v.exchange_rates.app.di.module

import android.content.Context
import androidx.room.Room
import com.github.serhii_v.exchange_rates.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase").build()

    @Provides
    @Singleton
    fun provideHubRepoDao(appDatabase: AppDatabase) = appDatabase.getExchangeRatesDao()

}