package com.github.serhii_v.exchange_rates.data.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CurrencyDetailsRatesDeserializer : JsonDeserializer<ExchangeRatesApi.Dto.CurrencyDetailsRates> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ExchangeRatesApi.Dto.CurrencyDetailsRates? {
        val rates = ExchangeRatesApi.Dto.CurrencyDetailsRates(ArrayList())
        for ((date, jsonElement) in json?.asJsonObject?.entrySet().orEmpty()) {
            if (date == null) continue
            for ((key, value) in jsonElement?.asJsonObject?.entrySet().orEmpty()) {
                if (key == null) continue
                val floatValue = value?.asFloat
                if (floatValue != null) {
                    rates.list.add(ExchangeRatesApi.Dto.RateOfDay(key, floatValue, date))
                    break
                }
            }
        }
        return rates
    }

}