package com.github.serhii_v.exchange_rates.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ExchangeRatesApi {

    companion object {
        private const val EXCHANGE_RATE = "latest"
        private const val HISTORY = "history"

        private const val PARAM_BASE_CURRENCY = "base"
        private const val PARAM_SYMBOLS = "symbols"
        private const val PARAM_START_DATE = "start_at"
        private const val PARAM_END_DATE = "end_at"
    }

    @GET(EXCHANGE_RATE)
    fun getExchangeRate(@Query(PARAM_BASE_CURRENCY) baseCurrency: String): Call<Dto.Currency>


    @GET(HISTORY)
    fun getCurrencyDetails(
        @Query(PARAM_BASE_CURRENCY) baseCurrency: String,
        @Query(PARAM_SYMBOLS) symbols: String,
        @Query(PARAM_START_DATE) startDate: String,
        @Query(PARAM_END_DATE) endDate: String
    ): Call<Dto.CurrencyDetails>

    sealed class Dto {
        companion object {
            private const val KEY_DATE = "date"
            private const val KEY_BASE = "base"
            private const val KEY_RATES = "rates"
            private const val KEY_START_AT = "start_at"
            private const val KEY_END_AT = "end_at"
        }


        data class Currency(@SerializedName(KEY_RATES) val rates: Map<String, Float>,
                            @SerializedName(KEY_BASE) val base: String,
                            @SerializedName(KEY_DATE) val date: String) : Dto()

        data class CurrencyDetails(@SerializedName(KEY_RATES) val rates: CurrencyDetailsRates,
                                   @SerializedName(KEY_BASE) val base: String,
                                   @SerializedName(KEY_START_AT) val startDate: String,
                                   @SerializedName(KEY_END_AT) val endDate: String
        ) : Dto()

        data class CurrencyDetailsRates(val list: ArrayList<RateOfDay>) : Dto()

        data class RateOfDay(val currency: String, val value: Float, val date: String) : Dto()

    }
}
