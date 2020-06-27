package com.github.serhii_v.exchange_rates.presentation.common.extension

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

private var format: DecimalFormat? = null

private fun createDecimalFormatForLocaleDefault(): DecimalFormat {
    return DecimalFormat.getNumberInstance(Locale.getDefault()) as DecimalFormat
}

private fun format(): DecimalFormat {
    return format ?: createDecimalFormatForLocaleDefault().also { format = it }
}

private fun roundMoneyValue(value: Float): Float {
    val result = BigDecimal(value.toDouble()).setScale(2, RoundingMode.HALF_EVEN)
    return result.toFloat()
}

fun Float.toMoneyValue(): String {
    return format().format(roundMoneyValue(this).toDouble())
}

