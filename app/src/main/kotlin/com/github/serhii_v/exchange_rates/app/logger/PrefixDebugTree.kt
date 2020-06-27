package com.github.serhii_v.exchange_rates.app.logger

import timber.log.Timber

class PrefixDebugTree : Timber.DebugTree() {
    companion object {
        private const val APP_TAG = "ExchangeRates"
        private const val CALL_STACK_INDEX = 8
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        val tag = super.createStackElementTag(getOriginalStackTraceElement(element))
        return "$APP_TAG: $tag : ${element.lineNumber}"
    }

    private fun getOriginalStackTraceElement(element: StackTraceElement): StackTraceElement {
        return if (element.fileName == "LoggerImpl.kt") {
            val elements = Throwable().stackTrace
            elements[CALL_STACK_INDEX]
        } else {
            element
        }
    }
}