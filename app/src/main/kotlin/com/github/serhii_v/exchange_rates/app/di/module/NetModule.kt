package com.github.serhii_v.exchange_rates.app.di.module

import com.github.serhii_v.exchange_rates.BuildConfig
import com.github.serhii_v.exchange_rates.data.api.CurrencyDetailsRatesDeserializer
import com.github.serhii_v.exchange_rates.data.api.ExchangeRatesApi
import com.github.serhii_v.exchange_rates.data.api.ExchangeRatesApi.Dto.CurrencyDetailsRates
import com.github.serhii_v.exchange_rates.domain.common.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetModule {

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun providesOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder,
                             httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(createConverterFactory())
            .client(okHttpClient)
            .build()

    @Provides
    fun providesExchangeRatesApi(retrofit: Retrofit): ExchangeRatesApi = retrofit.create(ExchangeRatesApi::class.java)

    private fun createConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(CurrencyDetailsRates::class.java, CurrencyDetailsRatesDeserializer())
                .create()
        )
    }
}
