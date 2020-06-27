package com.github.serhii_v.exchange_rates.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import java.util.*

class Preferences(context: Context) {

    private val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        const val CURRENT_CURRENCY = "current_currency"
        const val LAST_REQUEST_TIME = "last_request_time"
    }

    fun setCurrency(currentCurrency: String) {
        pref.edit { putString(CURRENT_CURRENCY, currentCurrency) }
    }

    fun getCurrency(): String? {
        return pref.getString(CURRENT_CURRENCY, null)
    }

    fun setLastRequestTime(time: Date) {
        pref.edit { putLong(LAST_REQUEST_TIME, time.time) }
    }

    fun getLastRequestTime(): Date? {
        val time = pref.getLong(LAST_REQUEST_TIME, -1)
        return if (time < 0) null else Date(time)
    }

}