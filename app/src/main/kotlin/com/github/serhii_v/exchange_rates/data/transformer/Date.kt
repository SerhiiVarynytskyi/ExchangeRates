package com.github.serhii_v.exchange_rates.data.transformer

import java.text.SimpleDateFormat
import java.util.*

val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)

fun Date.toApiString(): String {
    return format.format(this)
}

fun Date.getDateBeforeWeek(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.add(Calendar.DAY_OF_YEAR, -7)
    return calendar.time
}

fun Date.is10MinutesPassed(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = Date()
    calendar.add(Calendar.MINUTE, -10)
    return this.time < calendar.time.time
}

fun String.toDate(): Date? {
    return format.parse(this)
}